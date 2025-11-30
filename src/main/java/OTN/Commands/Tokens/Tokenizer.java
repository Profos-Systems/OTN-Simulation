/*

This file is part of OTN-Simulation.

OTN-Simulation is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

OTN-Simulation is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with OTN-Simulation. If not, see <https://www.gnu.org/licenses/>. 

*/

package OTN.Commands.Tokens;

import java.util.ArrayList;
import java.util.List;

public class Tokenizer {

    String src;
    int index = 0;

    public Tokenizer(String sourceCode){

        src = sourceCode;

    }

    public List<Token> tokenize() {

    StringBuilder buffer = new StringBuilder("");
    List<Token> tokens = new ArrayList<>();
    
    while (peek() != null) {

        if (Character.isWhitespace(peek())) {
        
            consume();
        
        }
        
        else if (Character.isAlphabetic(peek())) {
            
            buffer.append(consume()); 
            
            while ((peek() != null) && (Character.isLetterOrDigit(peek()) ||  peek() == '-' || peek() == '_')) {
            
                buffer.append(consume());
            
            }


            String value = buffer.toString();
            value = value.toUpperCase();
            
            switch (value) {
                case "ROADM", "WSS", "WSS_PORT" , "TRANSPONDER_CARD", "TRANSPONDER", "FIBER" -> tokens.add(new Token(Token.types.OBJECT, value));
                case "SPEED", "DUPLEX", "TX_POWER", "PORT_COUNT", "LABEL" , "PORT_INDEX", "LENGTH", "A_SIDE", "Z_SIDE" , "WAVELENGTH", "FREQUENCY", "NAME" -> tokens.add(new Token(Token.types.FIELD, value));
                case "ADD", "MODIFY", "REMOVE" , "CREATE", "SET" -> tokens.add(new Token(Token.types.ACTION, value));
                case "HELP" -> tokens.add(new Token(Token.types.HELP, value));
                default -> tokens.add(new Token(Token.types.VALUE, value));
            }
            
            buffer.setLength(0); 
        }

        else if(Character.isDigit((peek()))){

            while(peek() != null && Character.isDigit(peek())){
            
                buffer.append(consume());

            }

            int value = Math.abs((int)Double.parseDouble(buffer.toString()));

            tokens.add(new Token(value));

            buffer.setLength(0);

            if((peek() != null) && peek().equals('-')){

                buffer.append(consume());

                String range = buffer.toString();

                tokens.add(new Token(Token.types.RANGEHYPHEN, range));

            }

            if((peek() != null) && peek().equals(',')){

                buffer.append(consume());

                String range = buffer.toString();

                tokens.add(new Token(Token.types.RANGECOMA, range));
                
            }

        }

        else {
            
            consume();
        
        }
    }

    return tokens;
}

    private Character peek(){

        if(index >= src.length()){

            return null;

        } else{

            return src.charAt(index);
        }

    }

    private char consume(){

        return src.charAt(index++);

    }
    
}
