/*

This file is part of OTN-Simulation.

OTN-Simulation is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

OTN-Simulation is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with OTN-Simulation. If not, see <https://www.gnu.org/licenses/>. 

*/

package OTN.Commands.Parse;

import OTN.Commands.Tokens.Token;
import OTN.Commands.Parse.ParseTree.*;
import java.util.List;
import java.util.ArrayList;

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

        if(peek() != null && peek().type == Token.types.VALUE){

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

        for(int i = 0; i < tokens.size(); i++){

            System.out.println(peek(i).value);

        }

        while(peek() != null){

            ObjectNode deviceNode = null;
            ActionNode actionNode = null;
            ObjectNameNode deviceName = null;
            ObjectNode objectNode = null;
            ObjectNameNode objectName = null;
            ValueNode value = null;
            FieldNode field = null;
            RangeNode rangeNode = null;

            if(peek() != null && peek().type == Token.types.OBJECT){
                deviceNode = parseObject();
                System.out.println("Found device " + deviceNode.object.value);
            }
            
        
            if(peek() != null && peek().type == Token.types.HELP){
                
                consume(); 
                
                if(deviceNode != null){
                    statementNodes.add(new StatementNode(deviceNode, StatementNode.types.HELP));
                }
                else{
                    statementNodes.add(new StatementNode(StatementNode.types.HELP));
                }
            }

            else if(peek() != null && peek().type == Token.types.ACTION){
                actionNode = parseAction();
                System.out.println("Found action " + actionNode.actionToken.value);

                if(peek() != null && peek().type == Token.types.FIELD){
                    field = parseField();
                    System.out.println("Field value found " + field.field.value);
                    if(peek() != null && peek().type == Token.types.VALUE){
                        deviceName = parseObjectName();
                        System.out.println("Found device name " + deviceName.name.value);
                    }
                    if(peek() != null && peek().type == Token.types.VALUE){
                        value = parseValue();
                        System.out.println("Found value " + value.value.value);
                    }
                }

                if(peek() != null && peek().type == Token.types.VALUE){
                    deviceName = parseObjectName();
                    System.out.println("Found device name " + deviceName.name.value);
                }
                
                if(peek() != null && peek().type == Token.types.OBJECT && !actionNode.actionToken.value.equals("CREATE") && !actionNode.actionToken.value.equals("MODIFY")){
                    objectNode = parseObject();
                }
                
                if(peek() != null && peek().type == Token.types.VALUE){
                    objectName = parseObjectName();
                    System.out.println("Object Name found " + objectName.name.value);
                }
                
                if(peek() != null && peek().type == Token.types.VALUE){
                    value = parseValue();
                    System.out.println("Value found " + value.value.value);
                }
                if(peek() != null && peek().type == Token.types.INT){
                    rangeNode = parseRange();
                }
                
                if(deviceNode != null && deviceName != null && objectNode != null && objectName != null){
                        if(value != null && field == null){
                            statementNodes.add(new StatementNode(deviceNode, actionNode, deviceName, objectNode, objectName, value));
                        } else {
                            statementNodes.add(new StatementNode(deviceNode, actionNode, deviceName, objectNode, objectName));
                        }
                    }

                else if(deviceNode != null && deviceName != null && field != null && value != null && actionNode.actionToken.value.equals("MODIFY")){
                    
                    statementNodes.add(new StatementNode(deviceNode, actionNode, deviceName, field, value));
                    
                }

                else if(deviceNode != null && actionNode.actionToken.value.equals("CREATE") && rangeNode != null){

                    statementNodes.add(new StatementNode(deviceNode, actionNode, rangeNode));

                }

                else if(deviceNode != null  && actionNode.actionToken.value.equals("CREATE")){

                    statementNodes.add(new StatementNode(deviceNode, actionNode, deviceName));

                }
                    
                else {
    
                    if(peek() != null) {
                        consume();
                        System.out.println("No matching statements");
                    }
                }
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
