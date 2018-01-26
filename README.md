
There are many “calculator” programs using Reverse Polish Notation (RPN). RPN represents
expressions in which the operator symbol is placed AFTER the arguments being operated on.
Polish notation, in which the operator comes before the operands, was invented in the 1920s by
the Polish mathematician Jan Lucasiewicz. In the late 1950s, Australian philosopher and
computer scientist Charles L. Hamblin suggested placing the operator after the operands and
hence created reverse polish notation.
Your task will be to design and implement INTERPRETER program using POLISH NOTATION. It will
be console application where program read from input, line by line, interpret commands and
print the result(s) on the console.<br/>
# ALPHABET:
• digits (0-9) and period (.), <br/>
• letters(a-z, A-Z),<br/>
• delimiters( “(“, “)”, “,”)<br/>

# LEXEMES
• add (addition)<br/>
• sub (subtraction)<br/>
• mult (multiplication)<br/>
• div (division)<br/>
• move (assignment)<br/>
• get (read variable from console)<br/>
• put (print variable value to console)<br/>
• read (load source code from external file)<br/>
• run (execute currently loaded source code)<br/>
• end (terminate interpreter)<br/>

# Syntax
variable ::= letter { letter | digit }<br/>
filename ::= variable<br/>
number ::= digit { digit } { period } { digit }<br/>
statement ::= operator (operand, operand)<br/>
operator ::= add | sub | mult | div<br/>
operand ::= variable | statement<br/>
assignment ::= get(variable)<br/>
 Print “Enter {variable}: “ and read value from console. Store value to table of variables.<br/>
announcement ::= put(variable)<br/>
 Print “{variable}: “ following by value of {variable}. Pull value from table of variables.<br/>
load source code ::= read(filename)<br/>
 Read lines of program from “filename.cal” and print all lines to console.<br/>
execute program ::= run<br/>
 Executes (interprets) loaded code, line by line<br/>
terminate interpreter ::= end<br/>
 Exits to command line<br/>
 
# Program example: Comment/Result
get(x)<br/>
move(div(x,2),y)<br/>
put(y)<br/>
move(add(mult(x,2),y),z)<br/>
put(z)<br/>
move(sub(z,mult(3,y)),x)<br/>
put(x)<br/>
Enter x: (you enter 8)<br/>
y = x / 2<br/>
y: 4<br/>
z = x*2 + y<br/>
z: 20<br/>
x = z – y<br/>
x: 8<br/>
