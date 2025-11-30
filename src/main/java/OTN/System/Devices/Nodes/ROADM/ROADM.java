/*

This file is part of OTN-Simulation.

OTN-Simulation is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

OTN-Simulation is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with OTN-Simulation. If not, see <https://www.gnu.org/licenses/>. 

*/

package OTN.System.Devices.Nodes.ROADM;
import OTN.System.Devices.Cards.WSS.WSS;
import OTN.System.Devices.Cards.Transponder.TransponderCard;
import java.util.List;

public class ROADM {
    
    String name;
    List<WSS> wssList;
    List<TransponderCard> clientTransponderCards;
    List<TransponderCard> lineTransponderCards;

    public ROADM(){

    }

    public ROADM(String name){

        this.name = name;
    
    }
    
    public void setName(String name){

        this.name = name;

    }

    public ROADM(String name, WSS wss, TransponderCard client, TransponderCard line){

        this.name = name;
        wssList.add(wss);
        clientTransponderCards.add(client);
        lineTransponderCards.add(line);

    }

    public void assignWSS(WSS wss){

        wssList.add(wss);

    }

    public void assignClientTransponder(TransponderCard client){

        clientTransponderCards.add(client);

    }
     
    public void assignLineTransponder(TransponderCard line){

        lineTransponderCards.add(line);

    }


    public List<WSS> getWSS(){

        return wssList;

    }

    public WSS getWSS(int i){

        return wssList.get(i);

    }

    public List<TransponderCard> getClientTransponderCards(){

        return clientTransponderCards;

    }

    public TransponderCard getClientTransponderCards(int i){

        return clientTransponderCards.get(i);

    }

    public List<TransponderCard> getLineTransponderCards(){

        return lineTransponderCards;

    }

    public TransponderCard getLineTransponderCards(int i){

        return lineTransponderCards.get(i);

    }

    public String getName(){

        return name;

    }
    
    public StringBuilder listAllAttributes(){

        StringBuilder sb = new StringBuilder();
        
        sb.append("ROADM ");
        sb.append(name);
        sb.append( " has been assigned WSS ");

        for(int i = 0; i < wssList.size(); i++){

            sb.append(wssList.get(i).getName());
            sb.append(" ");

        }

        sb.append(", Transponder Line Side Card(s)");

         for(int i = 0; i < lineTransponderCards.size(); i++){

            sb.append(lineTransponderCards.get(i).getName());
            sb.append(" ");

        }
        
        sb.append(", and Transponder Client Side Card(s)");

        for(int i = 0; i < clientTransponderCards.size(); i++){

            sb.append(clientTransponderCards.get(i).getName());
            sb.append(" ");

        }




        return sb;

    }

}
