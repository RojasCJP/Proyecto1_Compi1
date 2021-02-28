package Analizadores;
import static Analizadores.Tokens.*;
%%
%class Lexer
%type Tokens
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r]+
%{
    public String lexeme;
%}
%%
(CONJ) {lexeme=yytext(); return CONJ;}
{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}
"\n" {return Linea;}
("{") {lexeme=yytext(); return Llave_Abre;}
("}") {lexeme=yytext(); return Llave_Cierra;}
((.)"~"(.)) {lexeme=yytext(); return Guion;}
(".") {lexeme=yytext(); return Punto;}
(",") {lexeme=yytext(); return Coma;}
(";") {lexeme=yytext(); return Punto_Coma;}
(":") {lexeme=yytext(); return Dos_Puntos;}
("|") {lexeme=yytext(); return Or;}
("*") {lexeme=yytext(); return Asterizco;}
("+") {lexeme=yytext(); return Suma;}
("?") {lexeme=yytext(); return Interrogacion;}
("<!") {lexeme=yytext(); return Comentario_Multi_Abre;}
("!>") {lexeme=yytext(); return Comentario_Multi_Cierra;}
("%") {lexeme=yytext(); return Porcentaje;}
("->") {lexeme=yytext(); return Asignacion;}
("'"(.)*"'") {lexeme=yytext(); return Cadena;}
("\""(.)*"\"") {lexeme=yytext(); return Cadena;}
{L}({L}|{D})* {lexeme=yytext(); return Identificador;}
("(-"{D}+")")|{D}+ {lexeme=yytext(); return Numero;}
 . {return ERROR;}
