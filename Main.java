/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package datastru;

import static datastru.Ranking.index1;
import static datastru.readFile.in;
import java.util.Scanner;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        readFile fileHandler = new readFile();

        // Load necessary files
        fileHandler.readStopFile("stop.txt");
        fileHandler.processFile("dataset.csv");

        System.out.println("- Welcome to the Search System -\n");

        int choice;
        do {
            // Display menu options
            System.out.println("Please choose an option:");
            System.out.println("\n1. Retrieve term");
            System.out.println("   - Options: 1. Index with Lists, 2. Inverted Index with Lists, 3. Inverted Index with BST");
            System.out.println("2. Boolean Query Retrieval");
            System.out.println("3. Ranked Query Retrieval");
            System.out.println("4. Show Indexed Documents");
            System.out.println("5. Count Documents in the Index");
            System.out.println("6. Count Unique Words (excluding stop words)");
            System.out.println("7. Display Inverted Index (Lists)");
            System.out.println("8. Display Inverted Index (BST)");
            System.out.println("9. Show Vocabulary and Token Count");
            System.out.println("10. Exit");
            System.out.print(">> ");
            choice = input.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Enter the retrieval option number:");
                    int option = input.nextInt();
                    input.nextLine(); // Consume newline
                    System.out.println("Enter the term to search:");
                    String searchTerm = input.nextLine().toLowerCase().trim();

                    switch (option) {
                        case 1:
                            System.out.println("Retrieving using Index with Lists...");
                            LinkedList<Integer> results1 = fileHandler.in.show_document_term(searchTerm);
                            System.out.println("Term: " + searchTerm + " [");
                            results1.display();
                            System.out.println("]");
                            break;
                        case 2:
                            System.out.println("Retrieving using Inverted Index with Lists...");
                            boolean found = fileHandler.inv.search_inverted(searchTerm);
                            if (found) {
                                fileHandler.inv.invertIndex.retrieve().display();
                            } else {
                                System.out.println("Term not found.");
                            }
                            break;
                        case 3:
                            System.out.println("Retrieving using Inverted Index with BST...");
                            boolean exists = fileHandler.invBST.search_invertedBST(searchTerm);
                            if (exists) {
                                fileHandler.invBST.iverindex.retrieve().display();
                            } else {
                                System.out.println("Term not found.");
                            }
                            break;
                        default:
                            System.out.println("Invalid option.");
                    }
                    break;
                case 2:
                    input.nextLine();
                    System.out.println("Enter Query to retrieve: ");
                    String qu = input.nextLine().toLowerCase();
                    qu = qu.replaceAll(" and ", " AND ");
                    qu = qu.replaceAll(" or ", " OR ");

                    System.out.println("what method do you want?: \n1-using inverted index\n2-using index\n3-using BST");
                    int y = input.nextInt();
                    if (y == 1) {
                        QueryProcessing Query = new QueryProcessing(fileHandler.inv, fileHandler.invBST);

                        LinkedList qq = QueryProcessing.BooleanQuery(qu);
                        fileHandler.displaydocuments(qq);
                    } else if (y == 2) {
                        QueryProcessingIndex Query = new QueryProcessingIndex(readFile.in);

                        LinkedList qq = QueryProcessingIndex.BooleanQuery(qu);
                        fileHandler.displaydocuments(qq);
                    } else if (y == 3) {
                        QueryProcessing Query = new QueryProcessing(fileHandler.inv, fileHandler.invBST);

                        LinkedList qq = QueryProcessing.BooleanQueryBST(qu);
                        fileHandler.displaydocuments(qq);
                    } else {
                        System.out.println("Invalid input");
                    }
                    break;

                case 3:
                    input.nextLine(); // Consume newline
                    System.out.println("Enter query for ranking:");
                    String rankingQuery = input.nextLine().toLowerCase();
                    Ranking ranker = new Ranking(fileHandler.invBST, in, rankingQuery);
                    ranker.insert_sortedLIST();
                    ranker.display_doc_with_score_usinglist();
                    break;
                case 4:
                    fileHandler.in.showDocuments();
                    System.out.println("-----------------------------");
                    break;
                case 5:
                    System.out.println("Total number of documents: " + fileHandler.in.documents.n);
                    break;
                case 6:
                    System.out.println("Unique words (excluding stop words): " + fileHandler.inv.invertIndex.n);
                    break;
                case 7:
                    fileHandler.inv.display_invertIndex();
                    break;
                case 8:
                    fileHandler.invBST.display_InverBST();
                    break;
                case 9:
                    System.out.println("Total Tokens: " + fileHandler.totalTokens);
                    System.out.println("Unique Words: " + fileHandler.numberQniqueWORD);
                    break;
                case 10:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }

        } while (choice != 10);

    }
}
