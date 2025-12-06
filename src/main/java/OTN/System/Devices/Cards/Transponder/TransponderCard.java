/*

This file is part of OTN-Simulation.

OTN-Simulation is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

OTN-Simulation is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with OTN-Simulation. If not, see <https://www.gnu.org/licenses/>. 

*/

package OTN.System.Devices.Cards.Transponder;
import OTN.System.Devices.Cards.Transponder.Assets.Transponder;

public class TransponderCard {
    
    int portCount;
    Transponder [] ports;
    String name;

    public TransponderCard(String name, int portCount){

        this.name = name;
        this.portCount = portCount;
        this.ports = new Transponder [portCount];
        
        for(int i = 0; i < ports.length; i++){
        
            ports[i] = new Transponder(1000, true, 2);

        }
    }

    public TransponderCard(){

    }

    public TransponderCard(String name){

        this.name = name;

    }

    public void setPort(int portCount){


        this.portCount = portCount;
        this.ports = new Transponder [portCount];

        for(int i = 0; i < ports.length; i++){

            ports[i] = new Transponder(1000, true, 2);

        }
    }

    public void setName(String name){

        this.name = name;


    }

    public Transponder getPort(int index){

        return ports[index];

    }

    public String getName(){

        return name;

    }

    public int getPortCount(){

        return portCount;

    }

}
