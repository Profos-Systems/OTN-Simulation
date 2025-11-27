/*

This file is part of OTN-Simulation.

OTN-Simulation is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

OTN-Simulation is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with OTN-Simulation. If not, see <https://www.gnu.org/licenses/>. 

*/

package OTN.Commands.CommandGeneration;

import OTN.Commands.Parse.ParseTree.StatementNode;
import OTN.System.Devices.Nodes.ROADM.ROADM;
import OTN.Network.Orchestration;
import OTN.System.Devices.Cards.WSS.WSS;
import OTN.System.Devices.Cards.Transponder.TransponderCard;

import java.util.List;


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
                    case StatementNode.types.INIT -> outputs.append(initCommandGenerate(stmt));
                    case StatementNode.types.RANGEINIT -> outputs.append(deviceRangeGenerate(stmt));
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
        int first = stmt.rangeNode.firstInt.intValue;
        int second = stmt.rangeNode.secondInt.intValue;
        output.append(first);
        output.append("-");
        output.append(second);
        output.append(" ");
        output.append(" which is ");
        int total = second - first;
        output.append(total);
        output.append(" ");
        output.append(stmt.deviceNode.object.value);
        output.append("s\n\n");
        

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
}
