$$
\begin{align}

    [\text{Program}] &\to [\text{Statement}] ^* \\
    
    [\text{Statement}] &\to

    \begin{cases}
        [\text{Device}] [\text{Action}] [\text{Device Name}] [\text{Object}] [\text{Object Name}] \\
        [\text{Device}] [\text{Action}] [\text{Device Name}] [\text{Field}] [\text{Value}] \\
        [\text{Device}] [\text{Action}] [\text{Device Name}] [\text{Object}] [\text{Range}] \\
        [\text{Device}] \text{CREATE} [\text{Device\_Name}] \\ 
        [\text{Device}] \text{CREATE} [\text{Range}] \\
        [\text{Device}] \text{ Help} \\
        \text{HELP} \\
        \text{END} \\
    \end{cases} \\

    [\text{Device}] &\to [\text{Object}] \\

    [\text{Action}] &\to

    \begin{cases}
        \text{ADD} \\
        \text{REMOVE} \\
        \text{MODIFY} \\
        \text{SET} \\
        \text{CREATE}
    \end{cases}\\

    [\text{Object}] &\to
    
    \begin{cases}
        [\text{WSS}] \\
        [\text{Port}] \\
        [\text{Transponder Card}]\\
        [\text{Transponder}]\\
        [\text{Fiber}] \\
    \end{cases} \\

    [\text{FIELD}] &\to
    
    \begin{cases}
        [\text{SPEED}] \\
        [\text{DUPLEX}] \\ 
        [\text{TX\_POWER}] \\
        [\text{PORT\_COUNT}] \\ 
        [\text{LABEL}] \\
        [\text{PORT\_INDEX}] \\
        [\text{LENGTH}] \\
        [\text{A\_SIDE}] \\
        [\text{Z\_SIDE}] \\
        [\text{WAVELENGTH}] \\
        [\text{FREQUENCY}] \\
    \end{cases}\\

    [\text{Range}] &\to [\text{Int}] \text{ "," OR "-" } [\text{Int}] \\

    [\text{Object Name}] &\to String \\

    [\text{*Optional* Value}] &\to String \\

\end{align}
$$