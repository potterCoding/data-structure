/**
 * @author sun
 * @date 2020/4/4 10:57
 * @description
 */
public class Solution {

    public int uniqueMorseRepresentations(String[] words) {

        //a~z的摩斯密码
        String[] codes = {".-","-...","-.-.","-..",".","..-.","--.","....","..",".---","-.-",".-..","--","-.","---",".--.","--.-",".-.","...","-","..-","...-",".--","-..-","-.--","--.."};
        BSTSet<String> set = new BSTSet<String>();
        for(String word: words){
            StringBuilder res = new StringBuilder();
            for(int i = 0 ; i < word.length() ; i ++){
                //因为题目描述中每个单词 words[i]只包含小写字母
                //我们需要减去a对应ASCII码的偏移量，就可以获的对应字符的摩斯密码
                res.append(codes[word.charAt(i) - 'a']);
            }
            //因为set集合中不能添加重复元素，所以我们的集合中不会有重复的单词
            set.add(res.toString());
        }

        return set.getSize();
    }

    public static void main(String[] args) {
        String[] words = {"gin", "zen", "gig", "msg"};
        int i = new Solution().uniqueMorseRepresentations(words);
        System.out.println(i);
    }

}
