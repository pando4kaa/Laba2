import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Warehouse {
    private static List<ProductsGroup> groups = new ArrayList<>();

    /**
     * дефолтні групи товарів: художня література, психологія та бізнес. кожна група товарів містить назву та опис.
     * кожна книга має назву, автора, опис, видавництво, кількість на складі, ціну за одиницю.
     */
    public static ProductsGroup defaultGroup1 = new ProductsGroup(
            "Художня література",
            "якийсь опис",
            Arrays.asList(
                    new Product("1984",
                            "Джордж Орвелл",
                            "Роман описує життя людей в тоталітарному суспільстві, де всі аспекти життя контролюються державою, " +
                                    "яка перетворюється на абсолютну тиранію. Головний герой роману, Вінстон Сміт, працює у Міністерстві правди, " +
                                    "де він займається переписуванням історії, щоб узгодити її з поточними потребами режиму. " +
                                    "Автор наголошує на тому, як потужна держава може контролювати не тільки думки та дії людей, " +
                                    "але й саме поняття правди та історії.",
                            "Видавництво Жупанського",
                            25,
                            180),

                    new Product("Записки українського самашедшого",
                            "Ліна Костенко",
                            "Роман написано від імені 35-річного комп’ютерного програміста, який на тлі особистої драми прискіпливо," +
                                    " глибоко й болісно сканує усі вивихи нашого глобалізованого часу. " +
                                    "У світі надмірної (дез)інформації і тотального відчуження він – заручник світових абсурдів – " +
                                    "прагне подолати комунікативну прірву між чоловіком і жінкою, між родиною і професією, між Україною і світом. " +
                                    "За жанровою стилістикою «Записки українського самашедшого» – насичений мікс художньої літератури, " +
                                    "внутрішніх щоденників, сучасного літописання й публіцистики.",
                            "А-ба-ба-га-ла-ма-га",
                            20,
                            320),

                    new Product("Кульбабове вино",
                            "Рей Бредбері",
                            "Літо у неіснуючому містечку Ґрін Таун 1928 року… Веселі пустощі чотирьох безтурботних хлопчаків… " +
                                    "І кульбабове вино, яке готує дідусь… Воно – як чарівна капсула часу, що вбирає у себе досвід минулих літ, " +
                                    "воно – як ностальгія за дитинством, у яке зможе хоча б на мить повернутися кожен, " +
                                    "хто візьме до рук фантастичну повість Рея Бредбері «Кульбабове вино». " +
                                    "Ця книга – острівець, на якому зупиняється час. Її сторінки дихають далекими " +
                                    "дитинно-безмежними спогадами – теплими, п’янкими, вічними, проте яких нам не спіймати ніколи.",
                            "Навчальна книга - Богдан",
                            17,
                            125)
            )
    );

    ProductsGroup defaultGroup2 = new ProductsGroup(
            "Психологія",
            "якийсь опис",
            Arrays.asList(
                    new Product("Стань сильнішим",
                            "Брене Браун",
                            "Це книга психолога та письменниці Брене Браун, яка досліджує тему вразливості та сили. " +
                                    "Вона пропонує читачеві розглянути зв'язок між цими двома концепціями та доводить, " +
                                    "що справжня сила полягає в тому, щоб відкрито висловлювати свої емоції та бути вразливим. " +
                                    "Автор використовує власні дослідження та приклади з життя, щоб допомогти читачам зрозуміти, " +
                                    "як можна використовувати вразливість, щоб стати сильнішим. " +
                                    "Книга популярна серед тих, хто бажає зробити крок у своєму розвитку та почати рухатися до своїх мрій.",
                            "Книжковий клуб \"Клуб Сімейного Дозвілля\"",
                            18,
                            155),

                    new Product("Усе замахало. Але надія є",
                            "Марк Менсон",
                            "\"Усе замахало. Але надія є\" - це книга відомого письменника та блогера Марка Менсона, " +
                                    "яка присвячена темі труднощів та життєвої надії. У книзі автор досліджує, " +
                                    "які проблеми виникають в сучасному світі, від токсичної культури до політичної нестабільності, " +
                                    "та пропонує способи, якими люди можуть знайти надію та духовне задоволення в незвичайних місцях. " +
                                    "Менсон використовує свій саркастичний стиль написання та гумор, " +
                                    "щоб зробити ці теми доступними та зрозумілими для широкої аудиторії. " +
                                    "Книга є важливим ресурсом для тих, хто шукає підтримку та натхнення у складних життєвих ситуаціях.",
                            "Наш Формат",
                            14,
                            290),

                    new Product("Як здобувати друзів і впливати на людей",
                            "Дейл Карнегі",
                            "Книга містить практичні поради та приклади про те, як змінити своє ставлення до людей " +
                                    "та знайти спільну мову з ними, навіть якщо вони зовсім різні від нас. " +
                                    "Автор показує, що здатність впливати на інших людей є важливою складовою успіху " +
                                    "в будь-якій сфері життя, від бізнесу до особистих відносин. " +
                                    "Книга стала класикою жанру та досі залишається популярною в усьому світі, " +
                                    "допомагаючи людям розуміти, як взаємодіяти з іншими людьми та досягати своїх цілей.",
                            "КМ-БУКС",
                            35,
                            135)

            )
    );
    ProductsGroup defaultGroup3 = new ProductsGroup(
            "Бізнес",
            "якийсь опис",
            Arrays.asList(
                    new Product("7 звичок надзвичайно ефективних людей",
                            "Стівен Кові",
                            "Ця книжка — світовий супербестселер, праця №1 у темі росту особистості! " +
                                    "Вона позитивно вплинула на життя мільйонів людей в усьому світі, серед яких Білл Клінтон, Ларрі Кінг, Стівен Форбс!" +
                                    "Керівництво навчить вас краще розуміти себе, визначати пріоритети, формулювати життєві цілі й досягати їх. " +
                                    "Це книжка для тих, хто хоче стати кращим у професійній діяльності, " +
                                    "максимально реалізувати свій творчий потенціал та назавжди змінити своє життя на краще.",
                            "Книжковий клуб \"Клуб Сімейного Дозвілля\"",
                            50,
                            265),

                    new Product("Мистецтво говорити. Таємниці ефективного спілкування",
                            "Джеймс Борг",
                            "Бізнес-тренер Джеймс Борґ, відомий своїми відкриттями в галузях спілкування, особистісного розвитку, " +
                                    "мови тіла та «контролю мислення», пропонує прості і дієві поради для кожної людини. " +
                                    "Його методика допоможе змінити спосіб мислення і поведінку в особистому житті й на роботі, " +
                                    "зробить життя цікавішим, насиченішим і позбавить зайвого стресу, " +
                                    "а ваші навички спілкування сягнуть геть нового рівня.",
                            "Фабула",
                            40,
                            265),

                    new Product("Думай і багатій",
                            "Наполеон Гілл",
                            "Коли Наполеон Гілл був ще зовсім юним, Дейл Карнегі розважливо поділився з ним таємною формулу успіху, " +
                                    "яку він вивів ґрунтуючись на досягненнях видатних людей. Відтоді Наполеон Гілл не тільки зумів багато " +
                                    "чого досягти в бізнесі та особистому житті, а й дав можливість всьому світу випробувати цю формулу на особистому досвіді. " +
                                    "Може здатися, що «Думай і багатій» обіцяє надто багато, але прочитавши та зрозумівши ту саму формулу успіху, " +
                                    "ви усвідомите, що вона дала вам набагато більше, ніж ви могли собі уявити. На відміну від більшості сучасних видань, " +
                                    "що пропонують готові рішення проблем, Наполеон Гілл дає дещо більше — він дає Ідею, " +
                                    "яка застосовується для всіх аспектів життя.",
                            "Книжковий клуб \"Клуб Сімейного Дозвілля\"",
                            37,
                            230)
            )
    );



    public Warehouse() {
        groups.add(defaultGroup1);
        groups.add(defaultGroup2);
        groups.add(defaultGroup3);
        writeToFile();
    }

    /**
     * додає нову групу товарів до списку groups, якщо група з такою ж назвою ще не існує
     * @param group
     */
    public static void addProductGroup(ProductsGroup group) {
        for (ProductsGroup existingGroup : groups) {
            if (existingGroup.getName().equals(group.getName())) {
                System.out.println("Група товарів з такою назвою вже існує.");
                return;
            }
        }
        groups.add(group);
        writeToFile();
        System.out.println("Група товарів додана успішно!");
    }

    /**
     * знаходить групу товарів за назвою oldName і змінює її на newName та опис на newDescription.
     * @param oldName
     * @param newName
     * @param newDescription
     */
    public static void editProductsGroup(String oldName, String newName, String newDescription) {
        for (ProductsGroup group : groups) {
            if (group.getName().equals(oldName)) {
                //перевірка, чи не міститься вже така група товарів за новою назвою
                for (ProductsGroup existingGroup : groups) {
                    if (existingGroup.getName().equals(newName) && !existingGroup.getName().equals(oldName)) {
                        System.out.println("Група товарів з такою назвою вже існує!");
                        return;
                    }
                }
                group.setName(newName);
                group.setDescription(newDescription);
                writeToFile();
                System.out.println("Група товарів відредагована успішно!");
                return;
            }
        }
        System.out.println("Групу товарів з такою назвою не знайдено!");
    }

    /**
     * видаляє групу товарів зі списку groups за її назвою groupName
     * @param groupName
     */
    public static void deleteProductsGroup(String groupName) {
        for (ProductsGroup group : groups) {
            if (group.getName().equals(groupName)) {
                groups.remove(group);
                writeToFile();
                System.out.println("Групу товарів успішно видалено");
                return;
            }
        }
        System.out.println("Групу товарів з такою назвою не знайдено");
    }

    /**
     * видаляє групу товарів та всі товари, які належать до цієї групи
     * @param name
     */
    public void deleteGroupWithProducts(String name) {
        for (ProductsGroup group : groups) {
            if (group.getName().equals(name)) {
                deleteAllProductsInGroup(group);//видаляє всі товари з групи
                groups.remove(group);
                writeToFile();
                System.out.println("Група товарів та всі товари в ній видалені успішно!");
                return;
            }
        }
        System.out.println("Групу товарів з такою назвою не знайдено!");
    }

    /**
     * видаляє всі товари з групи
     * @param group
     */
    private void deleteAllProductsInGroup(ProductsGroup group) {
        group.getProducts().clear();
    }

    /**
     * Додає новий товар до групи з назвою groupName, якщо група існує
     * @param groupName назва групи, до якої додається товар
     * @param product об'єкт товару, який додається
     */
    public void addProductToGroup(String groupName, Product product) {
        for (ProductsGroup group : groups) {
            if (group.getName().equals(groupName)) {
                group.getProducts().add(product);
                writeToFile();
                System.out.println("Товар успішно додано до групи " + groupName);
                return;
            }
        }
        System.out.println("Групу товарів з назвою " + groupName + " не знайдено!");
    }

    /**
     * знаходить товар за назвою name в групі товарів groupName та змінює його назву на newName
     * @param groupName
     * @param name
     * @param newName
     */
    public void editProductName(String groupName, String name, String newName) {
        for (ProductsGroup group : groups) {
            if (group.getName().equals(groupName)) {
                for (Product product : group.getProducts()) {
                    if (product.getName().equals(name)) {
                        //перевірка, чи не міститься вже товар з новою назвою
                        for (Product existingProduct : group.getProducts()) {
                            if (existingProduct.getName().equals(newName) && !existingProduct.getName().equals(name)) {
                                System.out.println("Товар з такою назвою вже існує!");
                                return;
                            }
                        }
                        product.setName(newName);
                        writeToFile();
                        System.out.println("Товар успішно відредаговано!");
                        return;
                    }
                }
                System.out.println("Товар з такою назвою не знайдено!");
                return;
            }
        }
        System.out.println("Групу товарів з такою назвою не знайдено!");
    }

    /**
     * знаходить товар за назвою name в групі товарів groupName та змінює його автора на newAuthor
     * @param groupName
     * @param name
     * @param newAuthor
     */
    public void editProductAuthor(String groupName, String name, String newAuthor) {
        for (ProductsGroup group : groups) {
            if (group.getName().equals(groupName)) {
                for (Product product : group.getProducts()) {
                    if (product.getName().equals(name)) {
                        product.setAuthor(newAuthor);
                        writeToFile();
                        System.out.println("Товар успішно відредаговано!");
                        return;
                    }
                }
                System.out.println("Товар з такою назвою не знайдено!");
                return;
            }
        }
        System.out.println("Групу товарів з такою назвою не знайдено!");

    }

    /**
     * знаходить товар за назвою name в групі товарів groupName та змінює його опис на newDescription
     * @param groupName
     * @param name
     * @param newDescription
     */
    public void editProductDescription(String groupName, String name, String newDescription) {
        for (ProductsGroup group : groups) {
            if (group.getName().equals(groupName)) {
                for (Product product : group.getProducts()) {
                    if (product.getName().equals(name)) {
                        product.setDescription(newDescription);
                        writeToFile();
                        System.out.println("Товар успішно відредаговано!");
                        return;
                    }
                }
                System.out.println("Товар з такою назвою не знайдено!");
                return;
            }
        }
        System.out.println("Групу товарів з такою назвою не знайдено!");
    }

    /**
     * знаходить товар за назвою name в групі товарів groupName та змінює його видавництво на newPublisher
     * @param groupName
     * @param name
     * @param newPublisher
     */
    public void editProductPublisher(String groupName, String name, String newPublisher) {
        for (ProductsGroup group : groups) {
            if (group.getName().equals(groupName)) {
                for (Product product : group.getProducts()) {
                    if (product.getName().equals(name)) {
                        product.setPublisher(newPublisher);
                        writeToFile();
                        System.out.println("Товар успішно відредаговано!");
                        return;
                    }
                }
                System.out.println("Товар з такою назвою не знайдено!");
                return;
            }
        }
        System.out.println("Групу товарів з такою назвою не знайдено!");
    }

    /**
     * знаходить товар за назвою name в групі товарів groupName та змінює його кількість на newQuantity
     * @param groupName
     * @param name
     * @param newQuantity
     */
    public void editProductQuantity(String groupName, String name, int newQuantity) {
        for (ProductsGroup group : groups) {
            if (group.getName().equals(groupName)) {
                for (Product product : group.getProducts()) {
                    if (product.getName().equals(name)) {
                        product.setQuantity(newQuantity);
                        writeToFile();
                        System.out.println("Товар успішно відредаговано!");
                        return;
                    }
                }
                System.out.println("Товар з такою назвою не знайдено!");
                return;
            }
        }
        System.out.println("Групу товарів з такою назвою не знайдено!");
    }

    /**
     * знаходить товар за назвою name в групі товарів groupName та змінює його ціну на newPrice
     * @param groupName
     * @param name
     * @param newPrice
     */
    public void editProductPrice(String groupName, String name, double newPrice) {
        for (ProductsGroup group : groups) {
            if (group.getName().equals(groupName)) {
                for (Product product : group.getProducts()) {
                    if (product.getName().equals(name)) {
                        product.setPrice(newPrice);
                        writeToFile();
                        System.out.println("Товар успішно відредаговано!");
                        return;
                    }
                }
                System.out.println("Товар з такою назвою не знайдено!");
                return;
            }
        }
        System.out.println("Групу товарів з такою назвою не знайдено!");
    }

    /**
     * cпочатку знаходить групу товарів, а потім видаляє сам товар з цієї групи, якщо він їй належить.
     * @param productName
     */
    public void deleteProduct(String productName) {
        for (ProductsGroup group : groups) { // проходимось по всіх групах товарів
            for (Product product : group.getProducts()) { // проходимось по всіх товарах у групі
                if (product.getName().equals(productName)) { // якщо знайдено товар з такою ж назвою
                    group.getProducts().remove(product); // видаляємо товар з групи
                    writeToFile();
                    System.out.println("Товар успішно видалено!");
                    return;
                }
            }
            System.out.println("Товар з такою назвою не знайдено!");
        }
        System.out.println("Групу товарів з такою назвою не знайдено!");
    }

    /**
     * Метод, який повертає список груп.
     * @return group ArrayList
     */
    public List<ProductsGroup> getGroups() {
        return groups;
    }

    /**
     * Знаходить групу за назвою
     * @param title назва шуканої групи
     * @return група
     */
    public ProductsGroup findGroupByTitle(String title){
        for (ProductsGroup existingGroup : groups) {
            if (existingGroup.getName().equals(title)) {
                return existingGroup;
            }
        }
        return null;
    }

    /**
     *записування даних зі списку груп товарів у файл "test.txt".
     */
    public static void writeToFile(){
        try {
            FileWriter fileWriter = new FileWriter("src/test.txt");
            for (ProductsGroup group : groups) {
                fileWriter.write(group + "\n");
            }
                fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}



