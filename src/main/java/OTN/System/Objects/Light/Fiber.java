/*

This file is part of OTN-Simulation.

OTN-Simulation is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

OTN-Simulation is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with OTN-Simulation. If not, see <https://www.gnu.org/licenses/>. 

*/

package OTN.System.Objects.Light;

public class Fiber {
    
    double length;
    final double ATTENUATION = .2;
    String label;

    public Fiber(){

    }

    public Fiber(double len, String lb){

        length = len;
        label = lb;
    }

    public Fiber(String name){

        label = name;

    }

    public void setName(String name){

        label = name;

    }

    public void setLength(double len){

        length = len;

    }

    public String getName(){
        
        return label;

    }

    public double getLength(){

        return length;

    }

    public void attenuateLight(Photons light){

        light.setPhotonResultantPower((light.getPhotonTXPower() * ATTENUATION));
    
    }

}
