package reward;


import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class Rewards {
        
        static class CustomerRewards {
                String name;
                int rewards[];
                
                public CustomerRewards(String name) {
                        this.name = name;
                        this.rewards = new int[13]; 
                }
                
                void addReward(int month, int reward) {
                        rewards[month] = reward;
                }

                @Override
                public String toString() {
                        String s = "Customer: " + name + "--";
                        for(int i=1; i<=12; i++) {
                                s += "Month " + i + ": Rewards "+":" + rewards[i] +";--" + "";
                        }
                        return s;
                }
                
        }
        
        public static int getRewards(int amount) {
                if(amount <= 50) {
                        return 0;
                }
                if(amount <= 100) {
                        return amount - 50;
                }
                return (amount - 100) * 2 + 50;
        }

        public static void main(String[] args) {
                
                // Lets say, dataset is stored in file: rewards.txt
                String fileName = "C:/Users/srujan/eclipse-workspace/Rewards/src/rewards";
                
                HashMap<String, CustomerRewards> customerRewards = new HashMap<>();
                
                try {
                        Scanner reader = new Scanner(new File(fileName));
                        
                        while(reader.hasNextLine()) {
                                String line = reader.nextLine();
                                String tokens[] = line.split(",");
                                String custName = tokens[0];
                                int saleAmount = Integer.parseInt(tokens[1]);
                                int reward = getRewards(saleAmount);
                                int month = Integer.parseInt(tokens[2].split("-")[1]);
                                
                                if(customerRewards.containsKey(custName)) {
                                        customerRewards.get(custName).addReward(month, reward);
                                } else {
                                        CustomerRewards c = new CustomerRewards(custName);
                                        c.addReward(month, reward);
                                        customerRewards.put(custName, c);
                                }
                        }
                        
                        reader.close();
                        
                        // Now print for all customers.
                        for(CustomerRewards customerReward: customerRewards.values()) {
                                System.out.println(customerReward);
                        }
                        
                } catch (FileNotFoundException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                }
                
                
                
                
        }

}
