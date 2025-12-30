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

**ROADM Name Assignment** ROADM MODIFY NAME {ROADM CURRENT NAME} {NEW ROADM NAME}

**WSS Name Assignment** WSS MODIFY NAME {WSS CURRENT NAME} {NEW WSS NAME}

**Transponder Card Name Assignment** TRANSPONDER_CARD MODIFY NAME {TRANSPONDER_CARD CURRENT NAME} {NEW TRANSPONDER_CARD NAME}

**Transponder Name Assignment** TRANSPONDER MODIFY NAME {TRANSPONDER CURRENT NAME} {NEW TRANSPONDER NAME}

**WSS Port Name Assignment** WSS_PORT MODIFY NAME {WSS_PORT CURRENT NAME} {NEW WSS_PORT NAME}

**Fiber Name Assignment** FIBER MODIFY NAME {FIBER CURRENT NAME} {NEW FIBER NAME}

**Range Field Assignment** WSS MODIFY NAME 1-10 WSS_Things (Changes the name of WSS node 1-10 to WSS_Things REALLY NOT RECOMMENDED)

**Transponder Speed Assignment** TRANSPONDER MODIFY SPEED TRANSPONDER1 1000.254

**Transponder Duplex Assignment** TRANSPONDER MODIFY DUPLEX TRANSPONDER1 FULL

**Transponder Wavelength Assignment** TRANSPONDER MODIFY WAVELENGTH TRANSPONDER1 1550

**Transponder Frequency Assignment** TRANSPONDER MODIFY FREQUENCY TRANSPONDER1 193.41

***Transponder Wavelength will be calculated based on frequency and vice versa, running both commands on the same transponder will just cause your data to be overwritten. This setting is meant for the client facing transponders, as DWDM transponders will have multiple wavelengths.***

### Help Commands

**General Help:** Help

## Commands To Be Implemented

### Card Object Mapping

**Need Mapping for All Objects**

### Object Specific Help

**Need to Create Help Handler For All Objects Except ROADM**

## Notes

**Need to write fully help docs for language, then use docs to generate help commands**

**Need to write proper error exception handling**


## Photonics Related Issues

**Create Conversion Between Binary Data And Photonic Envelope**

**Attempt to Use DP-16QAM**

**Track Fully Photonic Envelope Through The Whole Simulation**
    
**Possibly rewrite portions of the WSS to operate on the Photonic Envelope**

## Current Potential Issues

* Timing and Synchronization
* Use without a graphics card