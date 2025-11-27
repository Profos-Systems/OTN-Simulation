/*

This file is part of OTN-Simulation.

OTN-Simulation is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

OTN-Simulation is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with OTN-Simulation. If not, see <https://www.gnu.org/licenses/>. 

*/

package OTN.Network;
import OTN.System.Devices.Nodes.ROADM.ROADM;
import OTN.System.Devices.Cards.WSS.WSS;
import OTN.System.Devices.Cards.Transponder.TransponderCard;
import OTN.System.Devices.Cards.WSS.Assets.WSSPort;
import OTN.System.Devices.Cards.Transponder.Assets.Transponder;
import OTN.System.Objects.Light.Fiber;

import java.util.List;
import java.util.ArrayList;

public class Orchestration {

    List<ROADM> ROADMS = new ArrayList<>();
    List<WSS> WSSS = new ArrayList<>();
    List<TransponderCard> Transponder_Cards = new ArrayList<>();
    List<WSSPort> WSSPorts = new ArrayList<>();
    List<Transponder> Transponders = new ArrayList<>();
    List<Fiber> Fibers = new ArrayList<>();

    public Orchestration(){


    }

    public void addNode(ROADM node){

        ROADMS.add(node);

    }

    public void addNode(WSS node){

        WSSS.add(node);

    }

    public void addNode(TransponderCard node){

        Transponder_Cards.add(node);

    }

    public void addNode(WSSPort node){

        WSSPorts.add(node);

    }

    public void addNode(Transponder node){

        Transponders.add(node);

    }

    public void addNode(Fiber node){

        Fibers.add(node);

    }
}
