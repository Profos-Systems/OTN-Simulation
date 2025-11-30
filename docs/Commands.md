# Command List

This is a collection of all the commands for the simulation, all of which can be ran from the terminal.

## Commands That Have Been Implemented

### Initialization

**Example:** {Device} CREATE {*Device Name*}

**ROADM Initialization:** ROADM CREATE {*ROADM Name*}

**WSS Initialization:** WSS CREATE {*WSS Name*}

**Transponder Card Initialization:** TRANSPONDER_CARD CREATE {*Transponder Card Name*}

**Transponder Initialization:** TRANSPONDER CREATE {*Transponder Name*}

**Fiber Initialization:** FIBER CREATE {*Fiber Name*}

**WSS Port Initialization:** WSS_PORT CREATE {*WSS Port Name*}

**Range Initialization** {*Device*} CREATE *Int*-*Int*

***Example:*** WSS_PORT CREATE 1-9 (Creates Port's 1 Through 9)

***This approach will assign a name value to the port that is equal to the index of the given device. If needed, the naming convention will be RecursiveDeviceParent/InitializedDevice (For Example: ROADM 1 Transponder_Card 3 Port 4 1/3/4)***

### Field Value Assignments

**ROADM Name Assignemnt** ROADM MODIFY NAME {ROADM CURRENT NAME} {NEW ROADM NAME}

### Help Commands

**General Help:** Help

## Commands To Be Implemented

### Card Object Mapping

**Need Mapping for All Objects**

### Object Specific Help

**Need to Create Help Handler For All Objects Except ROADM**

### Field Value Assignments

**Need to Create Field Assignment Handlers For All Objects and Fields**

## Notes

**Need to Move Away From Proof of Concept For Command Generation, Move to Proof of Concept With Object Creation.**

**Need to write fully help docs for language, then use docs to generate help commands**

**Need to write proper error exception handling**

## Photonics Related Issues

**Create Conversion Between Binary Data And Photonic Envelope**

**Attempt to Use DP-16QAM**

**Track Fully Photonic Envelope Through The Whole Simulation**
    
* Possibly rewrite portions of the WSS to operate on the Photonic Envelope

## Current Potential Issues

* Timing and Synchronization
* Use without a graphics card