
//----------------------------------------------------
// The following code was generated by CUP v0.11b 20160615 (GIT 4ac7450)
//----------------------------------------------------

package Analizadores;

import java_cup.runtime.Symbol;
import Arbol.Nodo;
import java.util.ArrayList;
import java_cup.runtime.XMLElement;

/** CUP v0.11b 20160615 (GIT 4ac7450) generated parser.
  */
@SuppressWarnings({"rawtypes"})
public class Sintax extends java_cup.runtime.lr_parser {

 public final Class getSymbolContainer() {
    return sym.class;
}

  /** Default constructor. */
  @Deprecated
  public Sintax() {super();}

  /** Constructor which sets the default scanner. */
  @Deprecated
  public Sintax(java_cup.runtime.Scanner s) {super(s);}

  /** Constructor which sets the default scanner. */
  public Sintax(java_cup.runtime.Scanner s, java_cup.runtime.SymbolFactory sf) {super(s,sf);}

  /** Production table. */
  protected static final short _production_table[][] = 
    unpackFromStrings(new String[] {
    "\000\030\000\002\002\004\000\002\011\005\000\002\010" +
    "\004\000\002\010\003\000\002\010\004\000\002\010\003" +
    "\000\002\010\004\000\002\010\003\000\002\010\004\000" +
    "\002\010\003\000\002\010\005\000\002\010\004\000\002" +
    "\007\005\000\002\006\006\000\002\005\006\000\002\004" +
    "\005\000\002\004\005\000\002\004\004\000\002\004\004" +
    "\000\002\004\004\000\002\004\003\000\002\004\003\000" +
    "\002\003\010\000\002\002\004" });

  /** Access to production table. */
  public short[][] production_table() {return _production_table;}

  /** Parse-action table. */
  protected static final short[][] _action_table = 
    unpackFromStrings(new String[] {
    "\000\062\000\004\007\005\001\002\000\004\002\064\001" +
    "\002\000\012\003\014\004\010\005\013\024\007\001\002" +
    "\000\014\003\ufffe\004\ufffe\005\ufffe\010\ufffe\024\ufffe\001" +
    "\002\000\004\024\063\001\002\000\004\015\056\001\002" +
    "\000\014\003\052\004\010\005\013\010\050\024\007\001" +
    "\002\000\014\003\ufff8\004\ufff8\005\ufff8\010\ufff8\024\ufff8" +
    "\001\002\000\006\015\021\027\020\001\002\000\004\014" +
    "\017\001\002\000\014\003\ufffc\004\ufffc\005\ufffc\010\ufffc" +
    "\024\ufffc\001\002\000\014\003\ufffa\004\ufffa\005\ufffa\010" +
    "\ufffa\024\ufffa\001\002\000\014\003\ufff6\004\ufff6\005\ufff6" +
    "\010\ufff6\024\ufff6\001\002\000\020\007\033\012\025\016" +
    "\030\017\024\020\032\021\034\026\026\001\002\000\004" +
    "\026\022\001\002\000\004\014\023\001\002\000\014\003" +
    "\ufff4\004\ufff4\005\ufff4\010\ufff4\024\ufff4\001\002\000\020" +
    "\007\033\012\025\016\030\017\024\020\032\021\034\026" +
    "\026\001\002\000\020\007\033\012\025\016\030\017\024" +
    "\020\032\021\034\026\026\001\002\000\022\007\uffed\012" +
    "\uffed\014\uffed\016\uffed\017\uffed\020\uffed\021\uffed\026\uffed" +
    "\001\002\000\004\014\043\001\002\000\020\007\033\012" +
    "\025\016\030\017\024\020\032\021\034\026\026\001\002" +
    "\000\022\007\uffec\012\uffec\014\uffec\016\uffec\017\uffec\020" +
    "\uffec\021\uffec\026\uffec\001\002\000\020\007\033\012\025" +
    "\016\030\017\024\020\032\021\034\026\026\001\002\000" +
    "\004\005\036\001\002\000\020\007\033\012\025\016\030" +
    "\017\024\020\032\021\034\026\026\001\002\000\022\007" +
    "\uffee\012\uffee\014\uffee\016\uffee\017\uffee\020\uffee\021\uffee" +
    "\026\uffee\001\002\000\004\010\037\001\002\000\022\007" +
    "\ufff5\012\ufff5\014\ufff5\016\ufff5\017\ufff5\020\ufff5\021\ufff5" +
    "\026\ufff5\001\002\000\022\007\uffef\012\uffef\014\uffef\016" +
    "\uffef\017\uffef\020\uffef\021\uffef\026\uffef\001\002\000\020" +
    "\007\033\012\025\016\030\017\024\020\032\021\034\026" +
    "\026\001\002\000\022\007\ufff1\012\ufff1\014\ufff1\016\ufff1" +
    "\017\ufff1\020\ufff1\021\ufff1\026\ufff1\001\002\000\014\003" +
    "\ufff3\004\ufff3\005\ufff3\010\ufff3\024\ufff3\001\002\000\020" +
    "\007\033\012\025\016\030\017\024\020\032\021\034\026" +
    "\026\001\002\000\022\007\ufff2\012\ufff2\014\ufff2\016\ufff2" +
    "\017\ufff2\020\ufff2\021\ufff2\026\ufff2\001\002\000\022\007" +
    "\ufff0\012\ufff0\014\ufff0\016\ufff0\017\ufff0\020\ufff0\021\ufff0" +
    "\026\ufff0\001\002\000\014\003\uffff\004\uffff\005\uffff\010" +
    "\uffff\024\uffff\001\002\000\004\002\000\001\002\000\014" +
    "\003\ufff9\004\ufff9\005\ufff9\010\ufff9\024\ufff9\001\002\000" +
    "\004\014\055\001\002\000\014\003\ufffd\004\ufffd\005\ufffd" +
    "\010\ufffd\024\ufffd\001\002\000\014\003\ufffb\004\ufffb\005" +
    "\ufffb\010\ufffb\024\ufffb\001\002\000\014\003\ufff7\004\ufff7" +
    "\005\ufff7\010\ufff7\024\ufff7\001\002\000\004\005\057\001" +
    "\002\000\004\027\060\001\002\000\004\011\061\001\002" +
    "\000\004\014\062\001\002\000\014\003\uffeb\004\uffeb\005" +
    "\uffeb\010\uffeb\024\uffeb\001\002\000\014\003\uffea\004\uffea" +
    "\005\uffea\010\uffea\024\uffea\001\002\000\004\002\001\001" +
    "\002" });

  /** Access to parse-action table. */
  public short[][] action_table() {return _action_table;}

  /** <code>reduce_goto</code> table. */
  protected static final short[][] _reduce_table = 
    unpackFromStrings(new String[] {
    "\000\062\000\004\011\003\001\001\000\002\001\001\000" +
    "\014\002\011\003\005\005\014\006\015\010\010\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\012\002\050\003\046\005\052\006\053\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\006\004\026" +
    "\007\030\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\006\004\045\007\030\001\001\000\006" +
    "\004\043\007\030\001\001\000\002\001\001\000\002\001" +
    "\001\000\006\004\040\007\030\001\001\000\002\001\001" +
    "\000\006\004\037\007\030\001\001\000\002\001\001\000" +
    "\006\004\034\007\030\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\006\004" +
    "\041\007\030\001\001\000\002\001\001\000\002\001\001" +
    "\000\006\004\044\007\030\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001\000\002\001\001\000\002" +
    "\001\001\000\002\001\001\000\002\001\001\000\002\001" +
    "\001\000\002\001\001\000\002\001\001\000\002\001\001" +
    "\000\002\001\001\000\002\001\001\000\002\001\001\000" +
    "\002\001\001\000\002\001\001" });

  /** Access to <code>reduce_goto</code> table. */
  public short[][] reduce_table() {return _reduce_table;}

  /** Instance of action encapsulation class. */
  protected CUP$Sintax$actions action_obj;

  /** Action encapsulation object initializer. */
  protected void init_actions()
    {
      action_obj = new CUP$Sintax$actions(this);
    }

  /** Invoke a user supplied parse action. */
  public java_cup.runtime.Symbol do_action(
    int                        act_num,
    java_cup.runtime.lr_parser parser,
    java.util.Stack            stack,
    int                        top)
    throws java.lang.Exception
  {
    /* call code in generated class */
    return action_obj.CUP$Sintax$do_action(act_num, parser, stack, top);
  }

  /** Indicates start state. */
  public int start_state() {return 0;}
  /** Indicates start production. */
  public int start_production() {return 0;}

  /** <code>EOF</code> Symbol index. */
  public int EOF_sym() {return 0;}

  /** <code>error</code> Symbol index. */
  public int error_sym() {return 1;}



public ArrayList meVaAServir = new ArrayList();
    private Symbol s;

    public void syntax_error(Symbol s){
        this.s = s;
    }

    public Symbol getS(){
        return this.s;
    }


/** Cup generated class to encapsulate user supplied action code.*/
@SuppressWarnings({"rawtypes", "unchecked", "unused"})
class CUP$Sintax$actions {
  private final Sintax parser;

  /** Constructor */
  CUP$Sintax$actions(Sintax parser) {
    this.parser = parser;
  }

  /** Method 0 with the actual generated action code for actions 0 to 300. */
  public final java_cup.runtime.Symbol CUP$Sintax$do_action_part00000000(
    int                        CUP$Sintax$act_num,
    java_cup.runtime.lr_parser CUP$Sintax$parser,
    java.util.Stack            CUP$Sintax$stack,
    int                        CUP$Sintax$top)
    throws java.lang.Exception
    {
      /* Symbol object for return from actions */
      java_cup.runtime.Symbol CUP$Sintax$result;

      /* select the action based on the action number */
      switch (CUP$Sintax$act_num)
        {
          /*. . . . . . . . . . . . . . . . . . . .*/
          case 0: // $START ::= INICIO EOF 
            {
              Object RESULT =null;
		int start_valleft = ((java_cup.runtime.Symbol)CUP$Sintax$stack.elementAt(CUP$Sintax$top-1)).left;
		int start_valright = ((java_cup.runtime.Symbol)CUP$Sintax$stack.elementAt(CUP$Sintax$top-1)).right;
		Object start_val = (Object)((java_cup.runtime.Symbol) CUP$Sintax$stack.elementAt(CUP$Sintax$top-1)).value;
		RESULT = start_val;
              CUP$Sintax$result = parser.getSymbolFactory().newSymbol("$START",0, ((java_cup.runtime.Symbol)CUP$Sintax$stack.elementAt(CUP$Sintax$top-1)), ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()), RESULT);
            }
          /* ACCEPT */
          CUP$Sintax$parser.done_parsing();
          return CUP$Sintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 1: // INICIO ::= Llave_Abre CONTENIDO Llave_Cierra 
            {
              Object RESULT =null;

              CUP$Sintax$result = parser.getSymbolFactory().newSymbol("INICIO",7, ((java_cup.runtime.Symbol)CUP$Sintax$stack.elementAt(CUP$Sintax$top-2)), ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()), RESULT);
            }
          return CUP$Sintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 2: // CONTENIDO ::= CONTENIDO DECLARACION_CONJUNTOS 
            {
              Object RESULT =null;

              CUP$Sintax$result = parser.getSymbolFactory().newSymbol("CONTENIDO",6, ((java_cup.runtime.Symbol)CUP$Sintax$stack.elementAt(CUP$Sintax$top-1)), ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()), RESULT);
            }
          return CUP$Sintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 3: // CONTENIDO ::= DECLARACION_CONJUNTOS 
            {
              Object RESULT =null;

              CUP$Sintax$result = parser.getSymbolFactory().newSymbol("CONTENIDO",6, ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()), ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()), RESULT);
            }
          return CUP$Sintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 4: // CONTENIDO ::= CONTENIDO DECLARACION_REGEX 
            {
              Object RESULT =null;

              CUP$Sintax$result = parser.getSymbolFactory().newSymbol("CONTENIDO",6, ((java_cup.runtime.Symbol)CUP$Sintax$stack.elementAt(CUP$Sintax$top-1)), ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()), RESULT);
            }
          return CUP$Sintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 5: // CONTENIDO ::= DECLARACION_REGEX 
            {
              Object RESULT =null;

              CUP$Sintax$result = parser.getSymbolFactory().newSymbol("CONTENIDO",6, ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()), ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()), RESULT);
            }
          return CUP$Sintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 6: // CONTENIDO ::= CONTENIDO PREGUNTA_REGEX 
            {
              Object RESULT =null;

              CUP$Sintax$result = parser.getSymbolFactory().newSymbol("CONTENIDO",6, ((java_cup.runtime.Symbol)CUP$Sintax$stack.elementAt(CUP$Sintax$top-1)), ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()), RESULT);
            }
          return CUP$Sintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 7: // CONTENIDO ::= PREGUNTA_REGEX 
            {
              Object RESULT =null;

              CUP$Sintax$result = parser.getSymbolFactory().newSymbol("CONTENIDO",6, ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()), ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()), RESULT);
            }
          return CUP$Sintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 8: // CONTENIDO ::= CONTENIDO PORCENTAJES 
            {
              Object RESULT =null;

              CUP$Sintax$result = parser.getSymbolFactory().newSymbol("CONTENIDO",6, ((java_cup.runtime.Symbol)CUP$Sintax$stack.elementAt(CUP$Sintax$top-1)), ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()), RESULT);
            }
          return CUP$Sintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 9: // CONTENIDO ::= PORCENTAJES 
            {
              Object RESULT =null;

              CUP$Sintax$result = parser.getSymbolFactory().newSymbol("CONTENIDO",6, ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()), ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()), RESULT);
            }
          return CUP$Sintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 10: // CONTENIDO ::= CONTENIDO error Punto_Coma 
            {
              Object RESULT =null;

              CUP$Sintax$result = parser.getSymbolFactory().newSymbol("CONTENIDO",6, ((java_cup.runtime.Symbol)CUP$Sintax$stack.elementAt(CUP$Sintax$top-2)), ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()), RESULT);
            }
          return CUP$Sintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 11: // CONTENIDO ::= error Punto_Coma 
            {
              Object RESULT =null;

              CUP$Sintax$result = parser.getSymbolFactory().newSymbol("CONTENIDO",6, ((java_cup.runtime.Symbol)CUP$Sintax$stack.elementAt(CUP$Sintax$top-1)), ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()), RESULT);
            }
          return CUP$Sintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 12: // GRUPO ::= Llave_Abre Identificador Llave_Cierra 
            {
              String RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintax$stack.elementAt(CUP$Sintax$top-1)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintax$stack.elementAt(CUP$Sintax$top-1)).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$Sintax$stack.elementAt(CUP$Sintax$top-1)).value;
		RESULT="{"+a+"}";
              CUP$Sintax$result = parser.getSymbolFactory().newSymbol("GRUPO",5, ((java_cup.runtime.Symbol)CUP$Sintax$stack.elementAt(CUP$Sintax$top-2)), ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()), RESULT);
            }
          return CUP$Sintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 13: // PREGUNTA_REGEX ::= Identificador Dos_Puntos Cadena Punto_Coma 
            {
              Object RESULT =null;

              CUP$Sintax$result = parser.getSymbolFactory().newSymbol("PREGUNTA_REGEX",4, ((java_cup.runtime.Symbol)CUP$Sintax$stack.elementAt(CUP$Sintax$top-3)), ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()), RESULT);
            }
          return CUP$Sintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 14: // DECLARACION_REGEX ::= Identificador Asignacion REGEX Punto_Coma 
            {
              Object RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintax$stack.elementAt(CUP$Sintax$top-1)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintax$stack.elementAt(CUP$Sintax$top-1)).right;
		Nodo a = (Nodo)((java_cup.runtime.Symbol) CUP$Sintax$stack.elementAt(CUP$Sintax$top-1)).value;
		System.out.println("El valor de la expresión es: "+a.notacionPolaca());
    System.out.println("La expresion regular es: " + a.getValorNodo());
                        if (a != null) {
           meVaAServir.add(a);
       }
    
              CUP$Sintax$result = parser.getSymbolFactory().newSymbol("DECLARACION_REGEX",3, ((java_cup.runtime.Symbol)CUP$Sintax$stack.elementAt(CUP$Sintax$top-3)), ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()), RESULT);
            }
          return CUP$Sintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 15: // REGEX ::= Punto REGEX REGEX 
            {
              Nodo RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintax$stack.elementAt(CUP$Sintax$top-1)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintax$stack.elementAt(CUP$Sintax$top-1)).right;
		Nodo a = (Nodo)((java_cup.runtime.Symbol) CUP$Sintax$stack.elementAt(CUP$Sintax$top-1)).value;
		int bleft = ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()).left;
		int bright = ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()).right;
		Nodo b = (Nodo)((java_cup.runtime.Symbol) CUP$Sintax$stack.peek()).value;
		RESULT=new Nodo(".",a,b);
              CUP$Sintax$result = parser.getSymbolFactory().newSymbol("REGEX",2, ((java_cup.runtime.Symbol)CUP$Sintax$stack.elementAt(CUP$Sintax$top-2)), ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()), RESULT);
            }
          return CUP$Sintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 16: // REGEX ::= Or REGEX REGEX 
            {
              Nodo RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintax$stack.elementAt(CUP$Sintax$top-1)).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintax$stack.elementAt(CUP$Sintax$top-1)).right;
		Nodo a = (Nodo)((java_cup.runtime.Symbol) CUP$Sintax$stack.elementAt(CUP$Sintax$top-1)).value;
		int bleft = ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()).left;
		int bright = ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()).right;
		Nodo b = (Nodo)((java_cup.runtime.Symbol) CUP$Sintax$stack.peek()).value;
		RESULT=new Nodo("|",a,b);
              CUP$Sintax$result = parser.getSymbolFactory().newSymbol("REGEX",2, ((java_cup.runtime.Symbol)CUP$Sintax$stack.elementAt(CUP$Sintax$top-2)), ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()), RESULT);
            }
          return CUP$Sintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 17: // REGEX ::= Asterizco REGEX 
            {
              Nodo RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()).right;
		Nodo a = (Nodo)((java_cup.runtime.Symbol) CUP$Sintax$stack.peek()).value;
		RESULT=new Nodo("*",a,null);
              CUP$Sintax$result = parser.getSymbolFactory().newSymbol("REGEX",2, ((java_cup.runtime.Symbol)CUP$Sintax$stack.elementAt(CUP$Sintax$top-1)), ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()), RESULT);
            }
          return CUP$Sintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 18: // REGEX ::= Suma REGEX 
            {
              Nodo RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()).right;
		Nodo a = (Nodo)((java_cup.runtime.Symbol) CUP$Sintax$stack.peek()).value;
		RESULT=new Nodo("+",a,null);
              CUP$Sintax$result = parser.getSymbolFactory().newSymbol("REGEX",2, ((java_cup.runtime.Symbol)CUP$Sintax$stack.elementAt(CUP$Sintax$top-1)), ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()), RESULT);
            }
          return CUP$Sintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 19: // REGEX ::= Interrogacion REGEX 
            {
              Nodo RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()).right;
		Nodo a = (Nodo)((java_cup.runtime.Symbol) CUP$Sintax$stack.peek()).value;
		RESULT=new Nodo("?",a,null);
              CUP$Sintax$result = parser.getSymbolFactory().newSymbol("REGEX",2, ((java_cup.runtime.Symbol)CUP$Sintax$stack.elementAt(CUP$Sintax$top-1)), ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()), RESULT);
            }
          return CUP$Sintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 20: // REGEX ::= Cadena 
            {
              Nodo RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$Sintax$stack.peek()).value;
		RESULT=new Nodo(a,null,null);
              CUP$Sintax$result = parser.getSymbolFactory().newSymbol("REGEX",2, ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()), ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()), RESULT);
            }
          return CUP$Sintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 21: // REGEX ::= GRUPO 
            {
              Nodo RESULT =null;
		int aleft = ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()).left;
		int aright = ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()).right;
		String a = (String)((java_cup.runtime.Symbol) CUP$Sintax$stack.peek()).value;
		RESULT=new Nodo(a,null,null);
              CUP$Sintax$result = parser.getSymbolFactory().newSymbol("REGEX",2, ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()), ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()), RESULT);
            }
          return CUP$Sintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 22: // DECLARACION_CONJUNTOS ::= CONJ Dos_Puntos Identificador Asignacion Guion Punto_Coma 
            {
              Object RESULT =null;

              CUP$Sintax$result = parser.getSymbolFactory().newSymbol("DECLARACION_CONJUNTOS",1, ((java_cup.runtime.Symbol)CUP$Sintax$stack.elementAt(CUP$Sintax$top-5)), ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()), RESULT);
            }
          return CUP$Sintax$result;

          /*. . . . . . . . . . . . . . . . . . . .*/
          case 23: // PORCENTAJES ::= Porcentaje Porcentaje 
            {
              Object RESULT =null;

              CUP$Sintax$result = parser.getSymbolFactory().newSymbol("PORCENTAJES",0, ((java_cup.runtime.Symbol)CUP$Sintax$stack.elementAt(CUP$Sintax$top-1)), ((java_cup.runtime.Symbol)CUP$Sintax$stack.peek()), RESULT);
            }
          return CUP$Sintax$result;

          /* . . . . . .*/
          default:
            throw new Exception(
               "Invalid action number "+CUP$Sintax$act_num+"found in internal parse table");

        }
    } /* end of method */

  /** Method splitting the generated action code into several parts. */
  public final java_cup.runtime.Symbol CUP$Sintax$do_action(
    int                        CUP$Sintax$act_num,
    java_cup.runtime.lr_parser CUP$Sintax$parser,
    java.util.Stack            CUP$Sintax$stack,
    int                        CUP$Sintax$top)
    throws java.lang.Exception
    {
              return CUP$Sintax$do_action_part00000000(
                               CUP$Sintax$act_num,
                               CUP$Sintax$parser,
                               CUP$Sintax$stack,
                               CUP$Sintax$top);
    }
}

}
