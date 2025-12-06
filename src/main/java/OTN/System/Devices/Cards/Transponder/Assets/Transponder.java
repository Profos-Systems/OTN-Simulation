/*

This file is part of OTN-Simulation.

OTN-Simulation is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

OTN-Simulation is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with OTN-Simulation. If not, see <https://www.gnu.org/licenses/>. 

*/

package OTN.System.Devices.Cards.Transponder.Assets;

public class Transponder {
    
    double speed;
    boolean duplex; // Half (0) or Full (1)
    double rxSignalStrength_DBM;
    double txSignalStrength_DBM;
    public final double RXSENSITIVITY_DBM = -40;
    String name;

    public Transponder(double speed, boolean duplex, double txSignalStrength_DBM){

        speed = this.speed;
        duplex = this.duplex;
        txSignalStrength_DBM = this.txSignalStrength_DBM;
    }

    public Transponder(){


    }

    public Transponder(String name){

        this.name = name;

    }

    public String getName(){

        return name;

    }

    public double getSpeed(){

        return speed;

    }

    public boolean getDuplex(){

        return duplex;

    }

    public double getRXSignalStrength(){

        return rxSignalStrength_DBM;

    }

    public double getTXSignalStrength(){

        return txSignalStrength_DBM;
        
    }

    public void setRXSignal(double rxSignal){

        rxSignalStrength_DBM = rxSignal;

    }

    public double convertDBMToMW(double toConvert){

        double converted = Math.pow(10, (toConvert/10));

        return converted;

    }

    public void setTXSignalStrength(double signalStrength){

        txSignalStrength_DBM = signalStrength;
    }

    public void setSpeed(double speed){

        this.speed = speed;

    }

    public void setDuplex(boolean duplex){

        this.duplex = duplex;

    }

    public void setName(String name){

        this.name = name;

    }
}
