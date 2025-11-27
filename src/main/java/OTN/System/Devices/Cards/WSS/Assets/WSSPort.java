/*

This file is part of OTN-Simulation.

OTN-Simulation is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

OTN-Simulation is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with OTN-Simulation. If not, see <https://www.gnu.org/licenses/>. 

*/

package OTN.System.Devices.Cards.WSS.Assets;
import OTN.System.Devices.Cards.Transponder.Assets.Transponder;
public class WSSPort {
    
    String portSite;
    Transponder mappedTransponder;

    public WSSPort(String site, Transponder transponderToMap){

        portSite = site;
        mappedTransponder = transponderToMap;

    }

    public WSSPort(String site){

        portSite = site;

    }

    public String getName(){

        return portSite;

    }

    public double getIncomingSignalStrength(Transponder incoming){

        double signalStrengthMW = incoming.convertDBMToMW(incoming.getRXSignalStrength());

        return signalStrengthMW;
    }

    public void setOutgoingSignalStrength(Transponder outgoing, double signalStrength){

        outgoing.setTXSignalStrength(signalStrength);

    }

    public Transponder getMappedTransponder(){

        return mappedTransponder;

    }
}
