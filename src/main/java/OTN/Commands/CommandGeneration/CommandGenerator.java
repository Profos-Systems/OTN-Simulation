/*

This file is part of OTN-Simulation.

OTN-Simulation is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

OTN-Simulation is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with OTN-Simulation. If not, see <https://www.gnu.org/licenses/>. 

*/

package OTN.Commands.CommandGeneration;

import java.util.List;

import OTN.Commands.Parse.ParseTree.ObjectNameNode;
import OTN.Commands.Parse.ParseTree.RangeNode;
import OTN.Commands.Parse.ParseTree.StatementNode;
import OTN.Commands.Tokens.Token;
import OTN.Network.Orchestration;
import OTN.System.Devices.Cards.Transponder.Assets.Transponder;
import OTN.System.Devices.Cards.Transponder.TransponderCard;
import OTN.System.Devices.Cards.WSS.Assets.WSSPort;
import OTN.System.Devices.Cards.WSS.WSS;
import OTN.System.Devices.Nodes.ROADM.ROADM;
import OTN.System.Objects.Light.Fiber;


public class CommandGenerator {
    
    List <StatementNode> StatementNodes;
    Orchestration networkOrchestrator;

    public CommandGenerator(List <StatementNode> StatementNodes, Orchestration networkOrchestrator){

        this.StatementNodes = StatementNodes;
        this.networkOrchestrator = networkOrchestrator;

    }

    public StringBuilder generator(){

        StringBuilder outputs = new StringBuilder();


        if(StatementNodes != null && !StatementNodes.isEmpty()){

            for(int i = 0; i < StatementNodes.size(); i++){
            
                StatementNode stmt = StatementNodes.get(i);

                switch(stmt.type){

                    case StatementNode.types.HELP -> outputs.append(helpCommandGenerate());
                    case StatementNode.types.RANGEINIT -> outputs.append(deviceRangeGenerate(stmt));
                    case StatementNode.types.INIT -> outputs.append(initCommandGenerate(stmt));
                    case StatementNode.types.SET_VALUES -> outputs.append(setValues(stmt));
                    case StatementNode.types.RANGEMOD -> outputs.append(setRangeValues(stmt));
                    default -> outputs.append("Unable to generate commands!");
                }
            
            }

        }
        else {
        
            System.out.println("No nodes");
        
        }

        return outputs;

    }

    private StringBuilder initCommandGenerate(StatementNode stmt){

        StringBuilder output = new StringBuilder();

        switch(stmt.deviceNode.object.value){

            case "ROADM" -> output = initROADM(stmt);
            case "WSS" -> output = initWSS(stmt);
            case "TRANSPONDER_CARD" -> output = initTransponderCard(stmt);
            case "WSS_PORT" -> output = initWSSPORT(stmt);
            case "TRANSPONDER" -> output = initTransponder(stmt);
            case "FIBER" -> output = initFiber(stmt);
 
        }
        
        return output;
    }

    private StringBuilder helpCommandGenerate(){

        StringBuilder output = new StringBuilder();
        output.append("Displaying General Help Information!");
        output.append("\n\n");
        return output;
    }

    private StringBuilder deviceRangeGenerate(StatementNode stmt){

        StringBuilder output = new StringBuilder();
        output.append("Generating a range of ");
        int first = (int)stmt.rangeNode.firstInt.intValue;
        int second = (int)stmt.rangeNode.secondInt.intValue;
        output.append(first);
        output.append("-");
        output.append(second);
        output.append(" ");
        output.append(stmt.deviceNode.object.value);
        output.append("s\n\n");
        output.append("Which is ");
        int total = second - first;
        output.append(total);
        output.append(" ");
        output.append(stmt.deviceNode.object.value);
        output.append("s");
        output.append("\n\n");
        for (int i = first; i <= second; i++){

            String name = Integer.toString(i);
            Token nameToken = new Token(Token.types.VALUE, name);
            stmt.deviceName = new ObjectNameNode(nameToken);
            StatementNode rangeStmt = new StatementNode(stmt.deviceNode, stmt.actionNode, stmt.deviceName);
            output.append(initCommandGenerate(rangeStmt));

        }

        return output;

    }

    private StringBuilder initROADM(StatementNode stmt){

        StringBuilder output = new StringBuilder();

        ROADM ROADMNode = new ROADM(stmt.deviceName.name.value);

        output.append("Created ROADM: ");
        output.append(ROADMNode.getName());
        output.append("\n\n");  

        networkOrchestrator.addNode(ROADMNode);

        return output;

    }

    private StringBuilder initWSS(StatementNode stmt){

        StringBuilder output = new StringBuilder();

        WSS wss = new WSS(stmt.deviceName.name.value);

        output.append("Created WSS: ");
        output.append(wss.getName());
        output.append("\n\n");

        networkOrchestrator.addNode(wss);

        return output;

    }

    private StringBuilder initTransponderCard(StatementNode stmt){

        StringBuilder output = new StringBuilder();

        TransponderCard transponderCard = new TransponderCard(stmt.deviceName.name.value);

        output.append("Created Transponder Card: ");
        output.append(transponderCard.getName());
        output.append("\n\n");

        networkOrchestrator.addNode(transponderCard);

        return output;
    }

    private StringBuilder initWSSPORT(StatementNode stmt){

        StringBuilder output = new StringBuilder();

        WSSPort wssPort = new WSSPort(stmt.deviceName.name.value);

        output.append("Created WSS Port: ");
        output.append(wssPort.getName());
        output.append("\n\n");

        networkOrchestrator.addNode(wssPort);

        return output;
    
    }

    private StringBuilder initTransponder(StatementNode stmt){

        StringBuilder output = new StringBuilder();

        Transponder transponder = new Transponder(stmt.deviceName.name.value);

        output.append("Created Transponder: ");
        output.append(transponder.getName());
        output.append("\n\n");

        networkOrchestrator.addNode(transponder);

        return output;

    }

    private StringBuilder initFiber(StatementNode stmt){

        StringBuilder output = new StringBuilder();

        Fiber fiber = new Fiber(stmt.deviceName.name.value);

        output.append("Created Fiber: ");
        output.append(fiber.getName());
        output.append("\n\n");

        networkOrchestrator.addNode(fiber);

        return output;

    }

    private StringBuilder setValues(StatementNode stmt){

        StringBuilder output = new StringBuilder();

        switch(stmt.deviceNode.object.value){
                    
            case "ROADM" -> output.append(setROADMAttribute(stmt));
            case "WSS" -> output.append(setWSSAttribute(stmt));
            case "TRANSPONDER_CARD" -> output.append(setTransponderCardAttribute(stmt));
            case "TRANSPONDER" -> output.append(setTransponderAttribute(stmt));
            case "WSS_PORT" -> output.append(setWSSPortAttribute(stmt));
            case "FIBER" -> output.append(setFiberAttribute(stmt));
        }

        return output;

    }

    private StringBuilder setROADMAttribute(StatementNode stmt){

        StringBuilder output = new StringBuilder();
        ROADM node = networkOrchestrator.getROADMByName(stmt.deviceName.name.value);

        switch(stmt.fieldNode.field.value){

            case("NAME") -> {
                output.append("Changed name of ");
                output.append(stmt.deviceName.name.value);
                
                
                node.setName(stmt.valueNode.value.value);

                output.append(" to ");
                output.append(node.getName());
                output.append("\n\n");
            }
        
        }
        
        return output;

    }

    private StringBuilder setWSSAttribute(StatementNode stmt){

        StringBuilder output = new StringBuilder();
        WSS node = networkOrchestrator.getWSSByName(stmt.deviceName.name.value);

        switch(stmt.fieldNode.field.value){

            case("NAME") -> {
                output.append("Changed name of ");
                output.append(stmt.deviceName.name.value);

                node.setName(stmt.valueNode.value.value);

                output.append(" to ");
                output.append(node.getName());
                output.append("\n\n");
            }
            case("PORT_COUNT") -> {

                output.append("Changed port count of ");
                output.append(stmt.deviceName.name.value);

                node.setPortCount((int)stmt.valueNode.value.intValue);

                output.append(" to ");
                output.append(node.getPortCount());
                output.append("\n\n");

            }
            case("SITE") -> {

                output.append("Changed sites on ");
                output.append(stmt.deviceName.name.value);
                
                node.setPortNames(stmt.valueNode.value.arrayValue);

                output.append(" to ");
                output.append(node.getSiteLayout());

            }
        }

        return output;
    }

    private StringBuilder setTransponderCardAttribute(StatementNode stmt){

        StringBuilder output = new StringBuilder();
        TransponderCard node = networkOrchestrator.getTransponderCardByName(stmt.deviceName.name.value);

        switch(stmt.fieldNode.field.value){

            case("NAME") -> {
                output.append("Changed name of ");
                output.append(stmt.deviceName.name.value);
                
                
                node.setName(stmt.valueNode.value.value);

                output.append(" to ");
                output.append(node.getName());
                output.append("\n\n");
            }
        }

        return output;
    }
        
    private StringBuilder setTransponderAttribute(StatementNode stmt){

        StringBuilder output = new StringBuilder();
        Transponder node = networkOrchestrator.getTransponderByName(stmt.deviceName.name.value);

        switch(stmt.fieldNode.field.value){

            case("NAME") -> {
                output.append("Changed name of ");
                output.append(stmt.deviceName.name.value);
                
                
                node.setName(stmt.valueNode.value.value);

                output.append(" to ");
                output.append(node.getName());
                output.append("\n\n");
            }

            case("SPEED") -> {
                output.append("Changed speed of ");
                output.append(stmt.deviceName.name.value);

                node.setSpeed(stmt.valueNode.value.intValue);

                output.append(" to ");
                output.append(node.getSpeed());
                output.append("\n\n");
            }

            case("DUPLEX") ->{
                output.append("Changed Duplex of ");
                output.append(stmt.deviceName.name.value);

                if (stmt.valueNode.value.value.equals("HALF")){
                
                    node.setDuplex(false);

                }else if (stmt.valueNode.value.value.equals("FULL")){

                    node.setDuplex(true);

                }
                output.append(" to ");
                output.append(node.getDuplex());
                output.append("\n\n");
            }

            case ("WAVELENGTH") -> {

                output.append("Changed Wavelength of ");
                output.append(stmt.deviceName.name.value);
                
                node.setWavelength(stmt.valueNode.value.intValue);

                output.append(" to ");
                output.append(node.getWaveProperties());
                output.append("\n\n");

            }

            case ("FREQUENCY") -> {

                output.append("Changed Frequency of ");
                output.append(stmt.deviceName.name.value);

                node.setFrequency(stmt.valueNode.value.intValue);

                output.append(" to ");
                output.append(node.getWaveProperties());
                output.append("\n\n");

            }

            case("TX_POWER") ->{

                output.append("Changed TX Power of ");
                output.append(stmt.deviceName.name.value);

                node.setTXSignalStrength(stmt.valueNode.value.intValue);

                output.append(" to ");
                output.append(node.getTXSignalStrength());
                output.append("\n\n");

            }

        }

        return output;
    }
    
    private StringBuilder setWSSPortAttribute(StatementNode stmt){

        StringBuilder output = new StringBuilder();
        WSSPort node = networkOrchestrator.getWSSPortByName(stmt.deviceName.name.value);
        switch(stmt.fieldNode.field.value){

            case("NAME") -> {
                output.append("Changed name of ");
                output.append(stmt.deviceName.name.value);

                node.setName(stmt.valueNode.value.value);

                output.append(" to ");
                output.append(node.getName());
                output.append("\n\n");
            }
        }

        return output;
    }
    
    private StringBuilder setFiberAttribute(StatementNode stmt){

        StringBuilder output = new StringBuilder();
        Fiber node = networkOrchestrator.getFiberByName(stmt.deviceName.name.value);

        switch(stmt.fieldNode.field.value){

            case("NAME") -> {
                output.append("Changed name of ");
                output.append(stmt.deviceName.name.value);

                node.setName(stmt.valueNode.value.value);

                output.append(" to ");
                output.append(node.getName());
                output.append("\n\n");
            }
        }

        return output;
    }

    private StringBuilder setRangeValues(StatementNode stmt){
        if (stmt.rangeNode.type == RangeNode.types.HYPHEN){
            StringBuilder output = new StringBuilder();
            output.append("Changing values on a range of ");
            int first = (int)stmt.rangeNode.firstInt.intValue;
            int second = (int)stmt.rangeNode.secondInt.intValue;
            output.append(first);
            output.append("-");
            output.append(second);
            output.append(" ");
            output.append(stmt.deviceNode.object.value);
            output.append("s\n\n");
            output.append("Which is ");
            int total = second - first;
            output.append(total);
            output.append(" ");
            output.append(stmt.deviceNode.object.value);
            output.append("s");
            output.append("\n\n");
            for (int i = first; i <= second; i++){

                String name = Integer.toString(i);
                Token nameToken = new Token(Token.types.VALUE, name);
                stmt.deviceName = new ObjectNameNode(nameToken);
                StatementNode rangeStmt = new StatementNode(stmt.deviceNode, stmt.actionNode, stmt.deviceName, stmt.fieldNode, stmt.valueNode);
                output.append(setValues(rangeStmt));

            }

            return output;
        }

        else if (stmt.rangeNode.type == RangeNode.types.COMA){
            StringBuilder output = new StringBuilder();
            String[] tokenValues = new String[stmt.rangeNode.tokenList.size()];
            output.append("Comma separated range with size of ");
            output.append(stmt.rangeNode.tokenList.size());
            output.append("\n");

            for(int i = 0; i < stmt.rangeNode.tokenList.size(); i++){

                tokenValues[i] = stmt.rangeNode.tokenList.get(i).value;
                output.append(stmt.fieldNode.field.value);
                output.append(" : ");
                output.append(tokenValues[i]);

            }

            Token rangeArray = new Token(Token.types.VALUE, tokenValues);
            stmt.valueNode.value = rangeArray;
            StatementNode rangeStmt = new StatementNode(stmt.deviceNode, stmt.actionNode, stmt.deviceName, stmt.fieldNode, stmt.valueNode);
            output.append(setValues(rangeStmt));
            return output;

        }

        return null;

    }
    
}
