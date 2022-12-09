
package e1p1_hectorsabillon;

import java.util.ArrayList;


public class MaquinaEstados {
    
    public ArrayList<String> estados = new ArrayList<>();
    public ArrayList<String> estados_aceptacion = new ArrayList<>();
    public ArrayList<String> aristas = new ArrayList<>();
    public String estado_actual;
    
    
    public MaquinaEstados(String estados, String aristas){
        this.estados = splitStr(estados, ';');
        extractAcceptNodes();
        this.aristas = splitStr(aristas, ';');
        estado_actual = this.estados.get(0);
        
        
    }
    
    public ArrayList<String> splitStr(String str, char delim){
        ArrayList<String> temp = new ArrayList<>();
        String temp2 = "";
        for (int i = 0; i < str.length(); i++){
            if (str.charAt(i) != delim){
                temp2 += str.charAt(i);
            } else {
                temp.add(temp2);
                temp2 = "";
            }
            if (i+1 == str.length()){
                temp.add(temp2);
            }
       }
        System.out.println(estados);
        return temp;
    }
    
    public void extractAcceptNodes(){
        String sub = "";
        for (int i = 0; i < this.estados.size(); i++){
            if (this.estados.get(i).charAt(0) == '.'){
                sub = estados.get(i).substring(1);
                estados_aceptacion.add(sub);
                estados.set(i, sub);
                
            }
        }
        System.out.println(estados_aceptacion);
    }
    
    public String getArista(String str){
        String vac = "";
        for (int i = 0; i < aristas.size(); i++){
           if (aristas.get(i).contains(str)){
               return aristas.get(i);
           }
       }
        return vac;
    }
    
    public String computeStr(String str){
        String output = "";
        String rechazar = "rechazada";
        String aprobar = "aprobada";
        estado_actual = estados.get(0);
       /* int cont = 0;
        for (int i = 0; i < str.length(); i++){
            for (int j = 0; j < estados.size(); j++){
                input = estado_actual + "," + str.charAt(i) +  "," + estados.get(j);
                if (aristas.get(j).equals(input)){
                    estado_actual = estados.get(j);
                    //input += " " + estado_actual + "\n";
                    System.out.println(aristas.get(j)+ " " + input);
                } else {
                    cont++;
                }
            }
            if (cont == estados.size()){
                return rechazar;
            }
            cont = 0;
        }
        if (estados_aceptacion.contains(estado_actual)){
            return aprobar;
        }
        return input; 
        MAN COMO NO FUNCIONA?????
        */
       
       
       
       for (int i = 0; i < str.length(); i++){
           
           String ar = getArista(estado_actual + ',' + str.charAt(i));
           if (!ar.equals("")){
               estado_actual = ar.split(",")[2];
               output+= ar.split(",")[0] + ':' + str.charAt(i) + "->"  + ar.split(",")[2]+'\n';
           } else {
               output+= "Rechazada\n";
               return rechazar;
           }
       }
       if (estados_aceptacion.contains(estado_actual)){
           output+= "Aceptada\n";
            return output;
        } else {
           System.out.println(estado_actual);
           output+= "Rechazada\n";
           return output;
       }
    }
    
    
    
}
