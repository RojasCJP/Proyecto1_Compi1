package Analizadores;
import static Analizadores.Tokens.*;
%%
%class Lexer
%type Tokens
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r,\n]+
%{
    public String lexeme;
%}
%%
CONJ|
while {lexeme=yytext(); return Reservadas;}
{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}
"{" {return Llave_Abre;}
"}" {return Llave_Cierra;}
"-" {return Guion;}
"~" {return Guion;}
"." {return Punto;}
"," {return Coma;}
";" {return Punto_Coma;}
"|" {return Or;}
"*" {return Asterizco;}
"+" {return Suma;}
"?" {return Interogacion;}
"<!" {return Comentario_Multi_Abre;}
"!>" {return Comentario_Multi_Cierra;}
"%" {return Porcentaje;}
"\n" {return Linea;}
"'" {return Comilla;}
"\"" {return Doble_Comilla;}
{L}({L}|{D})* {lexeme=yytext(); return Identificador;}
("(-"{D}+")")|{D}+ {lexeme=yytext(); return Numero;}
 . {return ERROR;}