package Analizadores;
import static Analizadores.Tokens.*;
import Graficador.GraficadorErrores;
%%
%class Lexer
%type Tokens
%line
%char
%column
%column
%public
L=[a-zA-Z_]+
D=[0-9]+
espacio=[" ",\t,\r]+
especial=[\\]+
%{
    public String lexeme;
%}
%%
(CONJ) {lexeme=yytext(); return CONJ;}
{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}
"\n" {lexeme=yytext(); return Linea;}
{especial}("n"|"\""|"\'") {lexeme=yytext(); return Especial;}
("{") {lexeme=yytext(); return Llave_Abre;}
("}") {lexeme=yytext(); return Llave_Cierra;}
((.)"~"(.)) {lexeme=yytext(); return Guion;}
(".") {lexeme=yytext(); return Punto;}
([^("\'"|"\""|"}"|"n")](";")) {lexeme=yytext(); return Coma;}
([^("\'"|"\""|"}")](",")) {lexeme=yytext(); return Coma;}
(";") {lexeme=yytext(); return Punto_Coma;}
(":") {lexeme=yytext(); return Dos_Puntos;}
("|") {lexeme=yytext(); return Or;}
("*") {lexeme=yytext(); return Asterizco;}
("+") {lexeme=yytext(); return Suma;}
("?") {lexeme=yytext(); return Interrogacion;}
("<!"(.|"\n")*"!>") {/*ignore*/}
("%") {lexeme=yytext(); return Porcentaje;}
("-"{espacio}*">") {lexeme=yytext(); return Asignacion;}
("'"[^\']*"'") {lexeme=yytext(); return Cadena;}
("\""([^\"]|"\\\"")*"\"") {lexeme=yytext(); return Cadena;}
{L}({L}|{D})* {lexeme=yytext(); return Identificador;}
("(-"{D}+")")|{D}+ {lexeme=yytext(); return Numero;}
 . {System.out.println("error aqui"); GraficadorErrores.errores += "<tr><td>" + GraficadorErrores.contador + "</td><td>Lexico</td><td>Se encontro un error en los caracteres de entrada (caracter desconocido)</td><td>" + yyline + "</td><td>" + yycolumn + "</td></tr>\n";GraficadorErrores.contador++;}

