/*

This file is part of OTN-Simulation.

OTN-Simulation is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

OTN-Simulation is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with OTN-Simulation. If not, see <https://www.gnu.org/licenses/>. 

*/

package OTN.Commands.Tokens;

public class Token{

    public enum types {
        ACTION,
        OBJECT,
        VALUE,
        FIELD,
        INT,
        RANGE,
        HELP};

    public types type;
    public String value;
    public int intValue;

    public Token(types type, String value){

        this.type = type;
        this.value = value;
    }

    public Token(int value){

        type = types.INT;
        intValue = value;

    }
}
