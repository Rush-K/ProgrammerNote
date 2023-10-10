import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Comparator;

/* 
 * 문제 출처) 백준온라인저지 1339번 곱셈
 * 
 * 핵심 요약) 정렬
 * 
 * 핵심 설명) 1. INPUT STRING을 자릿수마다 List에 삽입 
 *  
 *          2. N 자릿수의 A 라면, 10^N * A로 치환 후 strDictionary에 삽입
 *          
 *          3. strDictionary의 모든 정보를 String 으로 변경 후 result List에 삽입
 * 
 *          4. 오름차순 정렬 후 크기가 큰 순서대로 알파벳에 숫자 부여 & 결과 값 계산 후 출력
*/
public class P1339
{
    public static HashMap<String, Integer> alphabetDictionary;
    
    public static void main(String[] args) throws NumberFormatException, IOException
    {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int number = 9;

        List<String> strs = new ArrayList<String>();
        List<String> result = new ArrayList<String>();
        HashMap<String, Long> strDictionary = new HashMap<String, Long>();

        // Add N inputs
        for (int i = 0; i < N; i++) strs.add(br.readLine());
        
        for (int i = 0; i < strs.size(); i++)
        {
            for (int j = strs.get(i).length() - 1; j >= 0; j--)
            {
                String alphabet = strs.get(i).substring(j, j + 1);
                Long posNum = (long) Math.pow(10, strs.get(i).length() - 1 - j);
                if (strDictionary.containsKey(alphabet)) strDictionary.put(alphabet, strDictionary.get(alphabet) + posNum);
                else strDictionary.put(alphabet, posNum);
            }
        }

       for (String key : strDictionary.keySet()) result.add(strDictionary.get(key) + ":" + key);
        
        Collections.sort(result, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2)
            {
                return Long.parseLong(o1.split(":")[0]) > Long.parseLong(o2.split(":")[0]) ? 0 : -1;
            }
        });

        //for (int i = 0; i < result.size(); i++) System.out.println(result.get(i));

        alphabetDictionary = new HashMap<String, Integer>();

        for (int i = result.size() - 1; i >= 0; i--)
        {
            String alphabet = result.get(i).split(":")[1];
            alphabetDictionary.put(alphabet, number--);
        }

        long finalResult = 0;

        for (int i = 0; i < strs.size(); i++)
        {
            finalResult += convertStr(strs.get(i));
        }

        System.out.println(finalResult);
    }
    
    public static long convertStr(String str)
    {
        String convertStr = "";

        for (int i = 0; i < str.length(); i++)
        {
            convertStr += Integer.toString(alphabetDictionary.get(str.substring(i, i + 1)));
        }

        return Long.parseLong(convertStr);
    }
}