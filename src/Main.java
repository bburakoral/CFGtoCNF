
import Business.ContextFreeGrammarTools;
import Model.EpsilonProduction;
import Business.Printer;
import Model.ContextFreeGrammar;
import Model.UniteProduction;
import Model.UselessSymbol;

public class Main {

    public static void main(String[] args){

        Printer printer = new Printer();
        ContextFreeGrammar epsilonProduction = new EpsilonProduction().getEpsilonProduction();
        ContextFreeGrammarTools contextFreeGrammarTools = new ContextFreeGrammarTools();
        ContextFreeGrammar uselessSymbol = new UselessSymbol().getContextFreeGrammar();
        ContextFreeGrammar unitProduction = new UniteProduction().getContextFreeGrammar();


        System.out.println("Before  Eliminate Epsilon-Production");
        printer.printCFG(epsilonProduction);
        ContextFreeGrammar epsilonProductionResult = contextFreeGrammarTools.EliminateEpsilonProduction(epsilonProduction);

        System.out.println("\n");
        System.out.println("After Eliminate Epsilon-Production ");
        printer.printCFG(epsilonProductionResult);


        System.out.println("\n");
        System.out.println("Before Eliminate Unite Production");
        printer.printCFG(unitProduction);


        System.out.println("\n");
        System.out.println("After Eliminate Unite Production");
        printer.printCFG(unitProduction);


        System.out.println("\n");
        System.out.println("Before Eliminate Useless Symbols");
        printer.printCFG(uselessSymbol);

       ContextFreeGrammar uselessSymbolResult = contextFreeGrammarTools.EliminateUselessSymbol(uselessSymbol);
        System.out.println("\n\n");
        System.out.println("After Eliminate Useless Symbols");
        printer.printCFG(uselessSymbolResult);





    }

}
