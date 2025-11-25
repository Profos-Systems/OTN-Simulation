/*

This file is part of OTN-Simulation.

OTN-Simulation is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

OTN-Simulation is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with OTN-Simulation. If not, see <https://www.gnu.org/licenses/>. 

*/

package OTN.Commands.Parse.ParseTree;
import OTN.Commands.Tokens.Token;
import java.util.List;

public class RangeNode {
    
    public Token firstInt;
    public Token hyphen;
    public Token secondInt;
    public List<Token> tokenList;
    public enum types{

        HYPHEN,
        COMA

    };
    public types type;

    public RangeNode(Token firstInt, Token secondInt){

        type = types.HYPHEN;
        this.firstInt = firstInt;
        this.secondInt = secondInt;

    }

    public RangeNode(List<Token> tokens){

        type = types.COMA;
        tokenList = tokens;

    }
    

}
