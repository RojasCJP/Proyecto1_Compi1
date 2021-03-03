package Analizadores;
import static Analizadores.Tokens.*;
%%
%class Lexer
%type Tokens
%column
%public
L=[a-zA-Z_]+
D=[0-9]+
espacio=[" ",\t,\r]+
especial=[\\n,\\\",\\\']
%{
    public String lexeme;
%}
%%
(CONJ) {lexeme=yytext(); return CONJ;}
{espacio} {/*Ignore*/}
"//".* {/*Ignore*/}
"\n" {lexeme=yytext(); return Linea;}
{especial} {lexeme=yytext(); return Especial;}
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
("-"{espacio}*">") {lexeme=yytext(); return Asignacion;}
("'"[^\']*"'") {lexeme=yytext(); return Cadena;}
("\""[^\"]*"\"") {lexeme=yytext(); return Cadena;}
{L}({L}|{D})* {lexeme=yytext(); return Identificador;}
("(-"{D}+")")|{D}+ {lexeme=yytext(); return Numero;}
 . {System.out.println("error aqui"); return ERROR;}
      //todo admitir demas caracteres
