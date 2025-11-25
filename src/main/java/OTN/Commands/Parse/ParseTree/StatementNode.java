/*

This file is part of OTN-Simulation.

OTN-Simulation is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later version.

OTN-Simulation is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.

You should have received a copy of the GNU General Public License along with OTN-Simulation. If not, see <https://www.gnu.org/licenses/>. 

*/

package OTN.Commands.Parse.ParseTree;

public class StatementNode {
    
    public ObjectNode deviceNode;
    public ActionNode actionNode;
    public ObjectNameNode deviceName;
    public ObjectNode objectNode;
    public ObjectNameNode objectNameNode;
    public ValueNode valueNode;
    public FieldNode fieldNode;
    public RangeNode rangeNode;
    
    public static enum types{
        ASSIGN_VALUE,
        ASSIGN,
        SET_VALUES,
        INIT,
        RANGE,
        HELP;
    }

    public types type;

    // Assigning devices to other devices, with optional value
    public StatementNode(ObjectNode device, ActionNode action, ObjectNameNode devName, ObjectNode object, ObjectNameNode name, ValueNode val){

        type = types.ASSIGN_VALUE;
        deviceNode = device;
        actionNode = action;
        deviceName = devName;
        objectNode = object;
        objectNameNode = name;
        valueNode = val;

    }

    // Assigning devices to other devices
    public StatementNode(ObjectNode device, ActionNode action, ObjectNameNode devName, ObjectNode object, ObjectNameNode name){

        type = types.ASSIGN;
        deviceNode = device;
        actionNode = action;
        deviceName = devName;
        objectNode = object;
        objectNameNode = name;
        valueNode = null;

    }

    // Help for Devices
    public StatementNode(ObjectNode device, types type){

        deviceNode = device;
        this.type = type;

    }

    public StatementNode(types type){

        // General Help
        if (type == types.HELP){

            this.type = types.HELP;

        }

    }

    // Setting values on certain devices
    public StatementNode(ObjectNode device, ActionNode action, ObjectNameNode name, FieldNode field, ValueNode value){

        deviceNode = device;
        actionNode = action;
        deviceName = name;
        fieldNode = field;
        valueNode = value;
        type = types.SET_VALUES;

    }

    // Initialize a device
    public StatementNode(ObjectNode device, ActionNode action, ObjectNameNode name){

        deviceNode = device;
        actionNode = action;
        deviceName = name;
        type = types.INIT;

    }

    // Create a range of devices
    public StatementNode(ObjectNode device, ActionNode action, RangeNode range){

        deviceNode = device;
        actionNode = action;
        rangeNode = range;
        type = types.RANGE;

    }

}
