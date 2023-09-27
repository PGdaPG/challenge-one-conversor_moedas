import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JOptionPane;

public class ConversorComView {
    public static void main(String[] args) throws Exception {
        int retornoMetodoFinal = 0;
        do {
            metodoInicial();
            retornoMetodoFinal = metodoFinal();
            switch (retornoMetodoFinal) {
                case 1:
                    JOptionPane.showMessageDialog(null, "Programa finalizado");
                    break;
                case 2:
                    JOptionPane.showMessageDialog(null, "Programa concluído");
                    break;
                default:
                    break;
            }
        } while (retornoMetodoFinal == 0);
        
    }
    private static int metodoFinal() {
        Object[] options = {"Yes",
                    "No",
                    "Cancel"};
                int retornoMetodoFinal = JOptionPane.showOptionDialog(null,
                "Deseja continuar",
                "Select an Option",
                JOptionPane.YES_NO_CANCEL_OPTION,
                JOptionPane.QUESTION_MESSAGE,
                null,
                options,
                options[0]);
                //JOptionPane.showMessageDialog(null,String.valueOf(n));
                return retornoMetodoFinal;                              
    }
    private static void metodoInicial() throws Exception {
        Object objetoEscolha;
        Object[] possibilities = {"Conversor de MoedasSimples", "Conversor com API"};
        objetoEscolha = JOptionPane.showInputDialog(
            null,
            "Escolha o Seu Conversor:",
            "Meu Conversor",
            JOptionPane.PLAIN_MESSAGE,
            null,
            possibilities,
            0);
        String stringEscolha = objetoEscolha.toString();
        switch (stringEscolha) {
            case "Conversor de MoedasSimples":
                conversorSimples();
                break;
            case "Conversor com API":
                conversorAPI();
                break;
            default:
                break;
        }
    }
    private static void conversorAPI() {
        JOptionPane.showMessageDialog(null, "Conversor em Desenvolvimento");
    }

    private static void conversorSimples() throws Exception {

        String input;
        double valor;
        do {
            input = JOptionPane.showInputDialog("Digite o valor\n" + "FORMATO: 000.00");
            //{name : "numeric18-2", pattern : "^(([\\d]{1,18})(\\,([\\d]{1,2}))?)$", message : "O campo deve ser preenchido com numeros ou ponto, a parte inteira aceita no maximo 18 digitos e a decimal no maximo 2."},

            Pattern pattern = Pattern.compile("^(([\\d]{1,18})(\\.([\\d]{1,2}))?)$");
            Matcher matcher = pattern.matcher(input);
            if (matcher.find()) {
                Object conversaoEscolhida;
                valor = Double.parseDouble(input);
                Object[] possibilitiesConvertion = {"De Reais para Dólares", 
                                                    "De Reais para Euros",
                                                    "De Reais para Libras",
                                                    "De Reais para Yenes",
                                                    "De Reais para Won Coreano",
                                                    "De Dólares para Reais",
                                                    "De Euros para Reais",
                                                    "De Libras para Reais",
                                                };
                        conversaoEscolhida = JOptionPane.showInputDialog(
                        null,
                        "Escolha a moeda para a qual você deseja converter seu dinheiro",
                        "Moedas",
                        JOptionPane.PLAIN_MESSAGE,
                        null,
                        possibilitiesConvertion,
                        0);
                StringBuilder mensagemResultado = new StringBuilder();
                mensagemResultado.append("O valor da conversão é de:").append(converter(valor,conversaoEscolhida));                            
                JOptionPane.showMessageDialog(null,mensagemResultado);
            }
            else {
                JOptionPane.showMessageDialog(null,"DIGITE NO FORMATO CERTO","Erro",JOptionPane.ERROR_MESSAGE);
            }

        } while (!input.matches("^(([\\d]{1,18})(\\.([\\d]{1,2}))?)$"));
    }
    private static String converter(double valor, Object conversaoEscolhida) throws Exception {

        Double taxaConversão;
        String stringResultado;
        switch (conversaoEscolhida.toString()) {
            case "De Reais para Dólares":
                taxaConversão = 4.96;
                valor = valor / taxaConversão;
                //String resultado = String.format("%.2f", valor);
                stringResultado = "US $" + String.format("%.2f", valor);
                return stringResultado;
            case "De Reais para Libras":
                taxaConversão = 6.05;
                valor = valor / taxaConversão;
                stringResultado = " £"+ String.format("%.2f", valor);
                return stringResultado;
            case "De Reais para Yenes":
                taxaConversão = 30.03;
                valor = valor * taxaConversão;
                stringResultado = " ¥" + String.format("%.2f", valor);
                return stringResultado;
            case "De Reais para Euros":
                taxaConversão = 5.25;
                valor = valor / taxaConversão;
                stringResultado = " €" + String.format("%.2f", valor);
                return stringResultado;
            case "De Reais para Won Coreano":
                taxaConversão = 270.21;
                valor = valor * taxaConversão;
                stringResultado = " ₩" + String.format("%.2f", valor);
                return stringResultado;                
            case "De Dólares para Reais":
                taxaConversão = 4.96;
                valor = valor * taxaConversão;
                stringResultado = " R$" + String.format("%.2f", valor);
                return stringResultado;                 
            case "De Euros para Reais":
                taxaConversão = 5.25;
                valor = valor * taxaConversão;
                stringResultado = " R$" + String.format("%.2f", valor);
                return stringResultado;   
            case "De Libra para Reais":
                taxaConversão = 6.05;
                valor = valor * taxaConversão;
                stringResultado = " R$" + String.format("%.2f", valor);
                return stringResultado;                                    
        }
        throw new Exception("Opção de conversão Invalida");
    }
}
