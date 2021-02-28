package Analizadores;
import java_cup.runtime.Symbol;
%%
%class LexerCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char
L=[a-zA-Z_]+
D=[0-9]+
espacio=[ ,\t,\r,\n]+
%{
    private Symbol symbol(int type, Object value){
      return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
      return new Symbol(type, yyline, yycolumn);
    }
%}
%%
CONJ {return new Symbol(sym.CONJ, yychar, yyline, yytext());}
{espacio} {/*Ignore*/}
("//"(.)*) {/*Ignore*/}
("{") {return new Symbol(sym.Llave_Abre, yychar, yyline, yytext());}
("}") {return new Symbol(sym.Llave_Cierra, yychar, yyline, yytext());}
((.)"~"(.)) {return new Symbol(sym.Guion, yychar, yyline, yytext());}
(".") {return new Symbol(sym.Punto, yychar, yyline, yytext());}
(",") {return new Symbol(sym.Coma, yychar, yyline, yytext());}
(";") {return new Symbol(sym.Punto_Coma, yychar, yyline, yytext());}
(":") {return new Symbol(sym.Dos_Puntos, yychar, yyline, yytext());}
("|") {return new Symbol(sym.Or, yychar, yyline, yytext());}
("*") {return new Symbol(sym.Asterizco, yychar, yyline, yytext());}
("+") {return new Symbol(sym.Suma, yychar, yyline, yytext());}
("?") {return new Symbol(sym.Interrogacion, yychar, yyline, yytext());}
("<!") {return new Symbol(sym.Comentario_Multi_Abre, yychar, yyline, yytext());}
("!>") {return new Symbol(sym.Comentario_Multi_Cierra, yychar, yyline, yytext());}
("%") {return new Symbol(sym.Porcentaje, yychar, yyline, yytext());}
("->") {return new Symbol(sym.Asignacion, yychar, yyline, yytext());}
("'"(.)*"'") {return new Symbol(sym.Cadena, yychar, yyline, yytext());}
("\""(.)*"\"") {return new Symbol(sym.Cadena, yychar, yyline, yytext());}
{L}({L}|{D})* {return new Symbol(sym.Identificador, yychar, yyline, yytext());}
("(-"{D}+")")|{D}+ {return new Symbol(sym.Numero, yychar, yyline, yytext());}
 . {return new Symbol(sym.ERROR, yychar, yyline, yytext());}