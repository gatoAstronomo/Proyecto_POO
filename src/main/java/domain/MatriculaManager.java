package domain;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MatriculaManager {
    public static boolean esMatriculaValida(String matricula){
        String regex = "^(\\d{8,9})[0-9kK](\\d{2})$";

        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(matricula);

        return matcher.matches();
    }
}
