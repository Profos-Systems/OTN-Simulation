/*

This file is part of OTN-Simulation.

OTN-Simulation is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

OTN-Simulation is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with OTN-Simulation. If not, see <https://www.gnu.org/licenses/>. 

*/

package OTN.Commands.Parse;

import java.util.ArrayList;
import java.util.List;

import OTN.Commands.Parse.ParseTree.ActionNode;
import OTN.Commands.Parse.ParseTree.FieldNode;
import OTN.Commands.Parse.ParseTree.ObjectNameNode;
import OTN.Commands.Parse.ParseTree.ObjectNode;
import OTN.Commands.Parse.ParseTree.RangeNode;
import OTN.Commands.Parse.ParseTree.StatementNode;
import OTN.Commands.Parse.ParseTree.ValueNode;
import OTN.Commands.Tokens.Token;

public class Parser {

    List<Token> tokens;
    int index = 0;
    
    public Parser(List<Token> toReadTokens){

        tokens = toReadTokens;

    }

    public ActionNode parseAction(){

        if(peek() != null && peek().type == Token.types.ACTION){

            ActionNode actionNode = new ActionNode(consume());

            return actionNode;

        }

        else{

            return null;

        }
    }

    public ObjectNode parseObject(){

        if(peek() != null && peek().type == Token.types.OBJECT){

            ObjectNode objectNode = new ObjectNode(consume());

            return objectNode;

        }

        else{

            return null;

        }
    }

    public ObjectNameNode parseObjectName(){

        if(peek() != null && peek().type == Token.types.VALUE){

            ObjectNameNode objectNameNode = new ObjectNameNode(consume());

            return objectNameNode;

        }

        else{

            return null;

        }
    }

    public ValueNode parseValue(){

        if(peek() != null && (peek().type == Token.types.VALUE || peek().type == Token.types.INT)){

            ValueNode valueNode = new ValueNode(consume());

            return valueNode;

        }

        else{

            return null;

        }
    }

    public FieldNode parseField(){

        if(peek() != null && peek().type == Token.types.FIELD){

            FieldNode fieldNode = new FieldNode(consume());

            return fieldNode;

        }

        else{

            return null;

        }
    }

    private RangeNode parseRange(){

        if((peek() != null) && (peek().type == Token.types.INT) && (peek(1) != null && peek(1).type == Token.types.RANGEHYPHEN)){

            RangeNode rangeNode = new RangeNode(consume(), consume(2));
            
            return rangeNode;

        }

        if((peek() != null) && (peek().type == Token.types.INT) && (peek(1) != null && peek(1).type == Token.types.RANGECOMA)){

            List <Token> tokenList = new ArrayList<>();
            while(peek() != null && (peek().type == Token.types.INT || peek().type == Token.types.RANGECOMA)){

                if(peek().type == Token.types.INT){
                
                    tokenList.add(consume());
                
                }else{

                    consume();

                }
            }

            RangeNode rangeNode = new RangeNode(tokenList);

            return rangeNode;

        }

        else{

            return null;

        }

    }

    public List<StatementNode> parse(){

        List<StatementNode> statementNodes = new ArrayList<>();

        while(peek() != null){

            ObjectNode deviceNode = null;
            ActionNode actionNode = null;
            ObjectNameNode deviceName = null;
            // ObjectNode objectNode = null;
            // ObjectNameNode objectName = null;
            ValueNode value = null;
            FieldNode field = null;
            RangeNode rangeNode = null;

            if(peek() != null && peek().type == Token.types.OBJECT){
            
                System.out.println("Object " + peek().value);
                deviceNode = parseObject();
            
            }
            
        
            if(peek() != null && peek().type == Token.types.HELP){
                
                consume(); 
                
                if(deviceNode != null){
                    statementNodes.add(new StatementNode(deviceNode, StatementNode.types.HELP));
                    continue;
                }
                else{
                    statementNodes.add(new StatementNode(StatementNode.types.HELP));
                    continue;
                }
            }

            if(peek() != null && peek().type == Token.types.ACTION){

                System.out.println("Action " + peek().value);
                actionNode = parseAction();
            
            }

            if(actionNode != null && actionNode.actionToken.value.equals("MODIFY")){
                
                if(peek() != null && peek().type == Token.types.FIELD){
                    
                    System.out.println("Field " + peek().value);
                    field = parseField();
                    
                    System.out.println("Next Token Type " + peek(1).type);

                    if((peek() != null && peek().type == Token.types.VALUE && peek(1) != null) && (peek(1).type == Token.types.VALUE || (peek(1).type == Token.types.INT && peek(2) == null || peek(2).type != Token.types.RANGECOMA || peek(2).type != Token.types.RANGEHYPHEN))) {
                        
                        System.out.println("Name " + peek().value);
                        System.out.println("Value " + peek(1).value);
                        deviceName = parseObjectName();
                        value = parseValue();
                                                
                        if(deviceNode != null){

                            statementNodes.add(new StatementNode(deviceNode, actionNode, deviceName, field, value));
                            continue;
                        
                        }
                    }

                    else if(peek() != null && peek().type == Token.types.INT){

                        rangeNode = parseRange();

                        if(peek() != null && (peek().type == Token.types.VALUE ||(peek().type == Token.types.INT && (peek().type != Token.types.RANGECOMA || peek().type != Token.types.RANGEHYPHEN)))){
                         
                            value = parseValue();

                        }

                        if(deviceNode != null){

                            statementNodes.add(new StatementNode(deviceNode, actionNode, field, rangeNode, value));
                            continue;

                        }

                    }
                }
            }

            else if(actionNode != null && actionNode.actionToken.value.equals("CREATE")){

                if(peek() != null && peek().type == Token.types.INT){

                    rangeNode = parseRange();

                    if(deviceNode != null){

                        statementNodes.add(new StatementNode(deviceNode, actionNode, rangeNode));
                        continue;
                    
                    }
                }
            
                if(peek() != null && peek().type == Token.types.VALUE){

                    deviceName = parseObjectName();

                    if(deviceName != null){
                        
                        statementNodes.add(new StatementNode(deviceNode, actionNode, deviceName));
                        continue;
                    
                    }
                }
            
            }

        else{

                System.out.print("Unknown Token: ");
                System.out.println(peek().value);
                consume();
                continue;

            }

        }

    return statementNodes;

    }

    private Token peek(){

        if (index >= tokens.size()){ 

            return null;

        } 
        else {
        
            return tokens.get(index); 
        
        }
    }

    private Token peek(int offset){

        int peekIndex = index + offset;
        if(peekIndex >= tokens.size()){

            return null;
        
        }
        else {
        
            return tokens.get(peekIndex);
       
        }

    }



    private Token consume(){

        if (index >= tokens.size()) {

            return null;
        
        }

        return tokens.get(index++);

    }

    private Token consume(int offset){

        Token last = null;

        for (int i = 0; i < offset; i++) {

            last = consume();
            
            if (last == null){
                
                break;
            }
        }
        return last;

    }

}
