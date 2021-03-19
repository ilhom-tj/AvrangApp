package tj.colibri.avrang.data.mock

import android.annotation.SuppressLint
import io.github.serpro69.kfaker.Faker
import tj.colibri.avrang.data.cart.CartItem
import tj.colibri.avrang.data.order.OrderItem
import tj.colibri.avrang.data.catalog.CatalogItem
import tj.colibri.avrang.data.categories.Category
import tj.colibri.avrang.data.categories.SubCategory
import tj.colibri.avrang.data.favorite.Favorite
import tj.colibri.avrang.data.order.OrderContainer
import tj.colibri.avrang.data.product.options.Option
import tj.colibri.avrang.data.product.options.ProductOptions
import tj.colibri.avrang.data.product.specifications.Specification
import tj.colibri.avrang.data.slider.SliderItem
import tj.colibri.avrang.data.checkout.spiner.BankSelect
import tj.colibri.avrang.data.checkout.spiner.ChekOutData
import tj.colibri.avrang.data.checkout.spiner.DedlineSelect
import tj.colibri.avrang.data.comments.Comment
import tj.colibri.avrang.data.filter_checkbox.FilterCheckBox
import tj.colibri.avrang.data.filter_radiobutton.FilterRadioButton
import tj.colibri.avrang.data.product.options.info.ProductInfo
import tj.colibri.avrang.data.user.User
import java.text.SimpleDateFormat
import kotlin.collections.ArrayList


object MockData {

//    // Slider
//    private val sliderImage1 = SliderItem(
//        1,
//        "Слайд №1",
//        "https://images.unsplash.com/photo-1506744038136-46273834b3fb?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1650&q=80"
//    )
//    private val sliderImage2 = SliderItem(
//        2,
//        "Слайд №2",
//        "https://images.unsplash.com/photo-1501785888041-af3ef285b470?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1650&q=80"
//    )
//    private val sliderImage3 = SliderItem(
//        3,
//        "Слайд №3",
//        "https://images.unsplash.com/photo-1494500764479-0c8f2919a3d8?ixid=MXwxMjA3fDB8MHxwaG90by1wYWdlfHx8fGVufDB8fHw%3D&ixlib=rb-1.2.1&auto=format&fit=crop&w=1650&q=80"
//    )
//
//    private val sliderImage4 = SliderItem(
//        4,
//        "Слайд №4",
//        "https://images.unsplash.com/photo-1559827291-72ee739d0d9a?ixid=MXwxMjA3fDB8MHxzZWFyY2h8OHx8bGFuZHNjYXBlfGVufDB8fDB8&ixlib=rb-1.2.1&auto=format&fit=crop&w=900&q=60"
//    )

   // var listOfSliderImages: MutableList<SliderItem> = arrayListOf(sliderImage1, sliderImage2, sliderImage3, sliderImage4)

//    //tj.colibri.avrang.data.ApiData.productinfo.tj.colibri.avrang.data.ApiData.product.tj.colibri.avrang.data.ApiData.product.ProductInfo.Product
//    @SuppressLint("SimpleDateFormat")
//    private val product1 = ProductCard(
//        1,
//        "Ультрабук ВОДА AD12ZSMIX",
//        true,
//        false,
//        true,
//        5,
//        92,
//        39990.99,
//        19995.49,
//        1234,
//        SimpleDateFormat("dd/MM/yyyy").parse("20/03/2020"),
//        5,
//        13
//    )
//
//    @SuppressLint("SimpleDateFormat")
//    private val product2 = ProductCard(
//        2,
//        "Ультрабук ОГОНЬ AD12ZSMIX",
//        true,
//        true,
//        true,
//        4,
//        5,
//        0.0,
//        3005.49,
//        12345,
//        SimpleDateFormat("dd/MM/yyyy").parse("20/03/2019"),
//        2,
//        11
//    )
//
//    @SuppressLint("SimpleDateFormat")
//    private val product3 = ProductCard(
//        3,
//        "Ультрабук Ветер ThinkPad AD12ZSMIX",
//        true,
//        true,
//        true,
//        4,
//        5,
//        0.0,
//        4005.49,
//        123456,
//        SimpleDateFormat("dd/MM/yyyy").parse("30/01/2018"),
//        10,
//        20
//    )
//    @SuppressLint("SimpleDateFormat")
//    private val product4 = ProductCard(
//        3,
//        "Ультрабук Зима ThinkPad AD12ZSMIX",
//        true,
//        true,
//        true,
//        2,
//        5,
//        0.0,
//        20005.49,
//        123456,
//        SimpleDateFormat("dd/MM/yyyy").parse("30/01/2018"),
//        10,
//        20
//    )
//
//    var listOfProducts : MutableList<ProductCard> = arrayListOf(product1, product2, product3,
//        product4)

    // Options
    private val optionsColor1 = Option(1, "Красный", "#FF0000")
    private val optionsColor2 = Option(2, "Черный", "#000000")

    private val optionsSize1 = Option(3, "35\"", "")
    private val optionsSize2 = Option(4, "42\"", "")
    private val optionsSize3 = Option(4, "55\"", "")

    private val productOption1 = ProductOptions(
        1, "Цвет", "color", listOf(optionsColor1, optionsColor2)
    )

    private val productOption2 = ProductOptions(
        1, "Размер", "size", listOf(optionsSize1, optionsSize2, optionsSize3)
    )

    val productOptions = listOf(productOption1, productOption2)

    //Specifications
    private val productSpec1 = Specification(1, "Поддержка Smart-TV", "Да")
    private val productSpec2 = Specification(2, "Технология HDR", "Да")
    private val productSpec3 = Specification(3, "Экран", "32\"/1920x1080 Пикс")
    private val productSpec4 = Specification(4, "Цифровое управление", "Да")

    val productSpecsList = listOf(productSpec1, productSpec2, productSpec3, productSpec4)

    //Catalog
    private val catalogItem1 = CatalogItem(1, "Акции", "ic_catalog_sales")
    private val catalogItem2 = CatalogItem(2, "Телевизоры и аудиотехника", "ic_catalog_tv_audio")
    private val catalogItem3 = CatalogItem(3, "Ноутбуки и компьютеры", "ic_catalog_laptops_pc")
    private val catalogItem4 = CatalogItem(4, "Бытовая техника", "ic_catalog_household")
    private val catalogItem5 = CatalogItem(5, "Мелкобытовая техника", "ic_catalog_small_household")
    private val catalogItem6 = CatalogItem(6, "Климатическая техника", "ic_catalog_air_conditioning")
    private val catalogItem7 = CatalogItem(7, "Мебель", "ic_catalog_furniture")
    private val catalogItem8 = CatalogItem(8, "Двери и фурнитура", "ic_catalog_doors")
    private val catalogItem9 = CatalogItem(9, "Аксессуары", "ic_catalog_accessories")

    val listOfCatalogItems = listOf(catalogItem1, catalogItem2, catalogItem3, catalogItem4, catalogItem5, catalogItem6, catalogItem7, catalogItem8, catalogItem9)

    //tj.colibri.avrang.data.ApiData.home.tj.colibri.avrang.data.ApiData.tj.colibri.avrang.data.ApiData.product.ProductInfo.Category.Categories and subcategories
    private val subCat1 = SubCategory(1, "Все телевизоры")
    private val subCat2 = SubCategory(2, "OLED телевизоры")
    private val subCat3 = SubCategory(3, "OLED 4K телевизоры")
    private val subCat4 = SubCategory(4, "OLED 8K телевизоры")
    private val subCat5 = SubCategory(5, "Full-HD телевизоры")
    private val subCat6 = SubCategory(6, "Прочее")

    private val cat1 = Category(1, "Телевизоры", listOf(subCat1, subCat2, subCat3, subCat4, subCat5, subCat6))
    private val cat2 = Category(2, "Плееры", emptyList())
    private val cat3 = Category(3, "Проекторы и экраны", emptyList())
    private val cat4 = Category(3, "Аудиотехника", emptyList())

    val listOfCategories = listOf(cat1, cat2, cat3, cat4)

    //Favorite
    private val favorite1 = Favorite(1, "Стиральная машина Haier Z1L5QBCD008", "SML03ZZ", 19995.49, 39990.99)
    private val favorite2 = Favorite(2, "Стиральная машина Haier Z1L5QBCD008", "SML03ZZ", 19995.49, 39990.99)
    private val favorite3 = Favorite(3, "Стиральная машина Haier Z1L5QBCD008", "SML03ZZ", 19995.49, 39990.99)
    private val favorite4 = Favorite(4, "Стиральная машина Haier Z1L5QBCD008", "SML03ZZ", 19995.49, 39990.99)

    val listOfFavorites : ArrayList<Favorite> = arrayListOf(favorite1, favorite2, favorite3,favorite4)

//    //Cart Items
//    private val cartItem1 = CartItem(1, "Стиральная машина Haier Z1L5QBCD008", "SML03ZZ", 60000.00, 120000.00,1)
//    private val cartItem2 = CartItem(2, "Стиральная машина Haier Z1L5QBCD008", "SML03ZZ", 60000.00, 120000.00,1)
//    private val cartItem3 = CartItem(3, "Стиральная машина Haier Z1L5QBCD008", "SML03ZZ", 60000.00, 120000.00,1)
//    private val cartItem4 = CartItem(4, "Стиральная машина Haier Z1L5QBCD008", "SML03ZZ", 60000.00, 120000.00,1)
//
//    val listOfCartItems = mutableListOf(cartItem1, cartItem2, cartItem3, cartItem4)


    private val bank_selector1 = BankSelect("Алиф Банк","https://i.pinimg.com/originals/ac/8d/42/ac8d42645c777541244e7ba10e375083.png")
    private val bank_selector2 = BankSelect("Эсхата Банк", "https://teams.by/img/club/2014_06/Eskhata-Khujand.gif")
    val listofBanks = listOf(bank_selector1, bank_selector2)

    private var dedline_selector1 = DedlineSelect("3 мес. х 19 995.49 TJS")
    private var dedline_selector2 = DedlineSelect("6 мес. х 20 995.49 TJS")
    val listofDedlineSelect = listOf(dedline_selector1, dedline_selector2)

    private var checkout  = ChekOutData("093214",190032.21,20.0,-81.0,"Корти милли",10.0,23132.32)

    private var checkbox1 = FilterCheckBox("Категория товара 1",23)
    private var checkbox2 = FilterCheckBox("Категория товара 2",33)
    val listofCheckBoxes = listOf(checkbox1, checkbox2)



    private var comment1 = Comment(12,
        "Рахимов Илхом",
        "https://mix.tj/img/s/7/76/189588.jpg",
        SimpleDateFormat("dd/MM/yyyy").parse("30/01/2021"),
        4,
        "Очень положительный отзыв",
        "Худший положительный отзыв",
        "Здесь мое мнение о товаре")
    private var comment2 = Comment(12,
        "Левый чел",
        "https://pbs.twimg.com/profile_images/1003896431919411200/QZy8WapJ_400x400.jpg",
        SimpleDateFormat("dd/MM/yyyy").parse("30/01/2021"),
        2,
        "Очень положительный отзыв",
        "Худший положительный отзыв",
        "Здесь мое мнение о товаре")
    val listOfComments = listOf(comment1, comment2)

    private var filterRadio1 = FilterRadioButton(1,"Новинки","Сначала новое поступление")

    private var filterRadio2 = FilterRadioButton(2,"По популярности","Сначала товары, которые щае покупают")

    private var filterRadio3 = FilterRadioButton(3,"По рейтингу","Сначала товары с высокими оценками")

    private var filterRadio4 = FilterRadioButton(4,"Сначала дешевые","Сначала товары с низкой стоимостью")

    private var filterRadio5 = FilterRadioButton(5,"Сначала дорогие","Сначала товары с высокой стоимостью")

    private var filterRadio6 = FilterRadioButton(6,"По скидкам","Сначала товары с большим % скидки")

    val listOfFilterRadio = listOf(filterRadio1,filterRadio2,filterRadio3,filterRadio4, filterRadio5,
        filterRadio6)

    val fakeToken = "gugugugugugugugu"

}