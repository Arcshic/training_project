/*
Thomas is developing a compiler for a functional programming language which
supports local function definitions. Your task is to help him transform a program with
local function definitions into a program with only global function definitions, by
"lifting" the local functions to the global scope.
As an example, here is a program for computing the nth Fibonacci number:
func fibonacci ( n )
func helper ( i , a , b )
if i == n then a else helper ( i + 1 , b , a + b )
end
helper ( 0 , 0 , 1 )
end
Figure 1: An example program with a local function definition
In this example, fibonacci is a global function and helper is a local function
defined inside fibonacci. The details are unimportant, but for the curious: The
language does not have an explicit return keyword; instead, the value of the
expression at the end of the function is returned implicitly. The if then else
construct is an expression (like C's ternary operator, cond ? val1 : val2). In the
        example program, the parameter i of helper takes on the values 0 to n; the
parameter a is the ith Fibonacci number; and b is the next Fibonacci number. The
first two Fibonacci numbers are 0 and 1, so the fibonacci function kicks off the
computation by passing i = 0, a = 0, b = 1 to helper.
One useful property of local functions is that they may refer to identifiers declared in
the enclosing scope. For instance, the helper function in Figure 1 refers to n, which
is a parameter of the enclosing fibonacci function. This makes it a little tricky to lift
local functions to global scope, because it might break references. The solution is to
introduce additional parameters to the lifted functions. For example, the program in
Figure 1 can be converted to the following program:
func fibonacci ( n )
helper ( n , 0 , 0 , 1 )
end
func helper ( n , i , a , b )
if i == n then a else helper ( n , i + 1 , b , a + b )
end
Figure 2: A transformed version of the program from Figure 1
Note how the parameter n was added to the signature of helper and to all the
locations where helper is called.
This technique requires that function names only appear in call expressions
        (functions cannot be passed unevaluated to other functions). Functions and
parameters can safely retain their names assuming all identifiers are unique. The
program in the input is guaranteed to satisfy these two requirements.
New Zealand Programming Contest 2023

To improve the efficiency of the transformed program, only the minimal set of
required parameters should be introduced. For example, if the helper function in
Figure 1 did not refer to n, then the transformation should not introduce n as an
additional parameter of helper.
Input
The input will be a valid program according to the following (heavily reduced) syntax:
A program consists of one or more function definitions
A function definition consists of:
a signature line with the function name and zero or more parameters:
func funcname ( paramname , paramname , ... )
followed by zero or more function definitions
followed by a line with an expression
followed by a line with the keyword end
An expression is one of the following (from highest to lowest precedence):
an integer literal
a paramname
a call expression of the form: funcname ( expression , expression , ... )
an add expression of the form: expression + expression
an equality expression of the form: expression == expression
an if-then-else expression of the form:
        if expression then expression else expression
An integer literal consists of one or more digits, without a leading zero (unless
                                                                                   it is the literal 0), and with at most nine digits in total
funcname and paramname consist of one to ten lowercase letters, and will not
be a keyword (func end if then else)
Notes
All tokens are separated by a single space. The program in the input will not be
indented, unlike Figures 1 and 2 (which are only indented for readability).
Likewise, the output program should not be indented.
All identifier references in the program will be within scope according to the
following lexical scoping rules:
The scope of a parameter name is the entire declaring function.
The scope of a global function is the entire program.
The scope of a locally defined function is the entire enclosing function.
        (Note that this means functions can be used before they are defined; see
Sample Input 2.)
Function names will only ever appear in call expressions (as the function being
        called). Parameter names will never be used as a function in a call expression.
        All identifiers (both function names and parameter names) will be unique
across the entire program. (This is ensured by a preprocessing step in the
compiler.)
Limits
The input program will have at most 1,000 characters, and it is guaranteed that the
output program will have at most 1,000 characters. There is no limit on the line
length (other than the limit implied by the program length).

Output
Output the transformed program, with all local function declarations converted to
        global functions, with the minimal set of introduced parameters.
For judging purposes, the following additional constraints are placed on the
transformation to ensure the output is unique:
The order of the functions in the transformed program must be the same as the
order in the original program, based on the location of their func keyword.
The introduced parameters must be sorted alphabetically, and must be added
at the start of the parameter list (before any original parameters).
Sample Input 1
        (This program is identical to Figure 1, but without indentation.)
func fibonacci ( n )
func helper ( i , a , b )
if i == n then a else helper ( i + 1 , b , a + b )
end
helper ( 0 , 0 , 1 )
end
Output for Sample Input 1
        (This program is identical to Figure 2, but without indentation.)
func fibonacci ( n )
helper ( n , 0 , 0 , 1 )
end
func helper ( n , i , a , b )
if i == n then a else helper ( n , i + 1 , b , a + b )
end
Sample Input 2
func main ( a , b )
func f ( )
a + g ( )
end
func g ( )
b + 1
end
f ( )
end

Output for Sample Input 2
func main ( a , b )
f ( a , b )
end
func f ( a , b )
a + g ( b )
end
func g ( b )
b + 1
end

        Explanation
In the original program, f and g are local functions defined inside main. When those
functions are lifted, the parameters a and b from main need to be introduced as
parameters of f, and b needs to be introduced as a parameter of g.*/

import java.util.*;

public class FunctionLifter {
    static class Function {
        String name;
        List<String> params;
        List<String> body;
        Set<String> dependencies;

        Function(String name, List<String> params, List<String> body) {
            this.name = name;
            this.params = params;
            this.body = body;
            this.dependencies = new HashSet<>();
        }

        void addDependency(String dep) {
            dependencies.add(dep);
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        List<Function> functions = new ArrayList<>();
        Map<String, Function> functionMap = new HashMap<>();
        Stack<Function> functionStack = new Stack<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            if (line.startsWith("func ")) {
                String[] parts = line.split(" ");
                String funcName = parts[1];
                String[] paramList = parts[2].substring(1, parts[2].length() - 1).split(", ");
                List<String> params = paramList[0].isEmpty() ? new ArrayList<>() : Arrays.asList(paramList);
                Function function = new Function(funcName, params, new ArrayList<>());
                functions.add(function);
                functionMap.put(funcName, function);
                functionStack.push(function);
            } else if (line.equals("end")) {
                functionStack.pop();
            } else {
                functionStack.peek().body.add(line);
                for (String token : line.split("[ ()+,]")) {
                    if (!token.isEmpty() && Character.isLowerCase(token.charAt(0))) {
                        if (!functionStack.peek().params.contains(token)) {
                            functionStack.peek().addDependency(token);
                        }
                    }
                }
            }
        }

        Map<String, Set<String>> finalParams = new HashMap<>();
        for (Function function : functions) {
            finalParams.put(function.name, new HashSet<>(function.params));
        }

        for (Function function : functions) {
            Set<String> allDeps = new HashSet<>();
            for (String dep : function.dependencies) {
                if (functionMap.containsKey(dep)) {
                    allDeps.addAll(finalParams.get(dep));
                } else {
                    allDeps.add(dep);
                }
            }
            finalParams.get(function.name).addAll(allDeps);
        }

        for (Function function : functions) {
            List<String> orderedParams = new ArrayList<>(finalParams.get(function.name));
            orderedParams.removeAll(function.params);
            Collections.sort(orderedParams);
            orderedParams.addAll(function.params);
            System.out.println("func " + function.name + " ( " + String.join(" , ", orderedParams) + " )");
            for (String line : function.body) {
                for (String dep : finalParams.get(function.name)) {
                    line = line.replaceAll("\\b" + dep + "\\b", dep);
                }
                System.out.println(line);
            }
            System.out.println("end");
        }
    }
}
