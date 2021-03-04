package Analizadores;
import java_cup.runtime.Symbol;
import Graficador.GraficadorErrores;
%%
%class LexerCup
%type java_cup.runtime.Symbol
%cup
%full
%line
%char
%column
%public
L=[a-zA-Z_]+
D=[0-9]+
espacio=[" ",\t,\r, \n]+
especial=[\\n,\\\",\\\']+
%{
    private Symbol symbol(int type, Object value){
      return new Symbol(type, yyline, yycolumn, value);
    }
    private Symbol symbol(int type){
      return new Symbol(type, yyline, yycolumn);
    }
%}
%%
CONJ {return new Symbol(sym.CONJ, yycolumn, yyline, yytext());}
{espacio} {/*Ignore*/}
("//"(.)*) {/*Ignore*/}
"\n" {return new Symbol(sym.Linea, yycolumn, yyline, yytext());}
{especial} {return new Symbol(sym.Especial, yycolumn, yyline, yytext());}
("{") {return new Symbol(sym.Llave_Abre, yycolumn, yyline, yytext());}
("}") {return new Symbol(sym.Llave_Cierra, yycolumn, yyline, yytext());}
((.)"~"(.)) {return new Symbol(sym.Guion, yycolumn, yyline, yytext());}
(".") {return new Symbol(sym.Punto, yycolumn, yyline, yytext());}
(",") {return new Symbol(sym.Coma, yycolumn, yyline, yytext());}
(";") {return new Symbol(sym.Punto_Coma, yycolumn, yyline, yytext());}
(":") {return new Symbol(sym.Dos_Puntos, yycolumn, yyline, yytext());}
("|") {return new Symbol(sym.Or, yycolumn, yyline, yytext());}
("*") {return new Symbol(sym.Asterizco, yycolumn, yyline, yytext());}
("+") {return new Symbol(sym.Suma, yycolumn, yyline, yytext());}
("?") {return new Symbol(sym.Interrogacion, yycolumn, yyline, yytext());}
("<!"(.|{espacio})*"!>") {/*ignore*/}
("%") {return new Symbol(sym.Porcentaje, yycolumn, yyline, yytext());}
("-"{espacio}*">") {return new Symbol(sym.Asignacion, yycolumn, yyline, yytext());}
("'"[^\']*"'") {return new Symbol(sym.Cadena, yycolumn, yyline, yytext());}
("\""[^\"]*"\"") {return new Symbol(sym.Cadena, yycolumn, yyline, yytext());}
{L}({L}|{D})* {return new Symbol(sym.Identificador, yycolumn, yyline, yytext());}
("(-"{D}+")")|{D}+ {return new Symbol(sym.Numero, yycolumn, yyline, yytext());}
 . {System.out.println("error aqui"); GraficadorErrores.errores += "<tr><td>" + GraficadorErrores.contador + "</td><td>Lexico</td><td>Se encontro un error en los caracteres de entrada (caracter desconocido)</td><td>" + yyline + "</td><td>" + yycolumn + "</td></tr>\n";GraficadorErrores.contador++;}