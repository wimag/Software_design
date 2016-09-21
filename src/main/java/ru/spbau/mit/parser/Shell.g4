grammar Shell;

@parser::header {
import java.util.*;
import ru.spbau.mit.Command;
}

@parser::members {
private HashMap<String, String> env;

public void setEnv(HashMap<String, String> env) {
    this.env = env;
}

private String getEnvVar(String name) {
    String key = name.substring(1, name.length());
    String value = env.get(key);
    return value == null ? "" : value;
}

private String replaceVar(String str) {
    String newStr = str.substring(1, str.length() - 1);
    StringBuilder result = new StringBuilder();
    String ref= "";
    boolean isRef = false;

    for (int i = 0; i < newStr.length(); i++) {
        char ch = newStr.charAt(i);

        if (!isRef) {
            if (ch == '$') isRef = true;
            else result.append(ch);
        } else {
            if (!(ref + ch).matches("[_a-zA-z][_a-zA-z0-9]*")) {
                String value = env.get(ref);
                result.append(value == null ? "" : value);
                i--;
                ref = "";
                isRef = false;
            } else {
                ref += ch;
            }
        }
    }

    String value = env.get(ref);
    result.append(value == null ? "" : value);

    return result.toString();
}
}

ref returns [String str]
    : REF {$str=getEnvVar($REF.text);};

strong_quotes returns [String str]
    : STRONG_QUOTES_STR {String newStr=$STRONG_QUOTES_STR.text;
                         newStr=newStr.substring(1, newStr.length() - 1);
                         $str=newStr;};

weak_quotes returns [String str]
    : WEAK_QUOTES_STR {$str=replaceVar($WEAK_QUOTES_STR.text);};

word returns [String str]
    : strong_quotes {$str=$strong_quotes.str;}
    | weak_quotes {$str=$weak_quotes.str;}
    | ref {$str=$ref.str;}
    | IDENTIFIER {$str=$IDENTIFIER.text;}
    | WORD {$str=$WORD.text;};

assignment returns [Command cmd]
    : IDENTIFIER
      ASSIGN
      word {$cmd=new Command(Command.Type.VAR, $IDENTIFIER.text, Arrays.asList($word.str));};

program returns [Command cmd]
        locals [List<String> list=new ArrayList<>()]
    : ( word {$list.add($word.str);} )+ {String name=$list.get(0);
                                        $list.remove(0);
                                        $cmd=new Command(Command.Type.PROG, name, $list);};

start returns [List<Command> list=new ArrayList<>()]
    : assignment {$list=Arrays.asList($assignment.cmd);}
    | program {$list.add($program.cmd);} ( PIPE program {$list.add($program.cmd);} )*
    |  {$list=null;};


WS: [\t\n ] -> skip;
ASSIGN: '=';
PIPE: '|';
STRONG_QUOTES_STR: '\'' CHAR* '\'';
WEAK_QUOTES_STR: '"' CHAR* '"';
REF: '$' IDENTIFIER;
IDENTIFIER: [_a-zA-z][_a-zA-z0-9]*;
WORD: (~[ \t\n'"$|=])+;

fragment CHAR: ('\u0020' .. '\u007F') | ('\\' ('t' | 'n' | '"' | '\'' | '\\'));
