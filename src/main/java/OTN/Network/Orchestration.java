/*

This file is part of OTN-Simulation.

OTN-Simulation is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

OTN-Simulation is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with OTN-Simulation. If not, see <https://www.gnu.org/licenses/>. 

*/

package OTN.Network;
import OTN.System.Devices.Nodes.ROADM.ROADM;
import OTN.System.Devices.Cards.WSS.WSS;
import java.util.List;
import java.util.ArrayList;

public class Orchestration {

    public List<ROADM> ROADMS = new ArrayList<>();
    public List<WSS> WSSS = new ArrayList<>();
    
    public Orchestration(){


    }

    public void addNode(ROADM node){

        ROADMS.add(node);

    }

    public void addNode(WSS node){

        WSSS.add(node);

    }

}
