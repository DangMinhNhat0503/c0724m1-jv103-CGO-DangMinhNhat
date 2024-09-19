class Product{
    id;
    name;
    category;
    price;
    image;
    constructor(id,name, category, price, image) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
        this.image = image;
    }
}
let products = [];
products.push(new Product(1,"Bò Kobe","Bò", 100000,"hocbaijs/image_case_study/bo-kobe.jpeg"));
products.push(new Product(2,"Bò Wagyu","Bò", 120000,"hocbaijs/image_case_study/bo-wagyu.jpeg"));
products.push(new Product(3,"Bò Tơ Củ Chi","Bò",80000,"hocbaijs/image_case_study/bo-to-cu-chi.jpeg"));
products.push(new Product(4,"Heo Rừng", "Heo", 40000,"hocbaijs/image_case_study/heo-rung.jpeg"));
products.push(new Product(5,"Heo Ăn Chuối","Heo",20000,"hocbaijs/image_case_study/heo-an-chuoi.jpeg"));
products.push(new Product(6,"Gà Tre","Gà",15000,"hocbaijs/image_case_study/ga-tre.jpeg"));
products.push(new Product(7,"Gà Đen","Gà",50000,"hocbaijs/image_case_study/ga-den.jpeg"));
products.push(new Product(8,"Vịt Quay","Vịt",80000,"hocbaijs/image_case_study/vit-quay.jpeg"));
products.push(new Product(9,"Vịt Xiêm","Vịt",40000,"hocbaijs/image_case_study/vit-xiem.jpeg"));
products.push(new Product(10,"Tôm Sú","Tôm",30000,"hocbaijs/image_case_study/tom-su.jpeg"));
products.push(new Product(11,"Tôm Mũ Ni","Tôm",80000,"hocbaijs/image_case_study/tom-mu-ni.jpeg"));
products.push(new Product(12,"Cua Huỳnh Đế","Cua",300000,"hocbaijs/image_case_study/cua-huynh-de.jpeg"));
products.push(new Product(13,"Cua Hoàng Đế","Cua",1900000,"hocbaijs/image_case_study/cua-hoang-de.jpeg"));
products.push(new Product(14,"Cá Lóc","Cá",40000,"hocbaijs/image_case_study/ca-loc.jpeg"));
products.push(new Product(15,"Cá Chép","Cá",50000,"hocbaijs/image_case_study/ca-chep.jpeg"));

console.log(products);

for (let i = 0; i < products.length; i++) {
    let card = document.createElement("div");
    card.classList.add("card",products[i].category);
    //Quan trọng phải có hidden nè, để không hiển thị khi trang tải đầu tiên
    card.setAttribute("hidden", "");

    // console.log(card.classList.contains(products[i].category));

    let imageContainer = document.createElement("div");
    imageContainer.classList.add("image-container");

    let image = document.createElement("img");
    image.setAttribute("src", products[i].image);
    image.setAttribute("alt", "Hình lỗi");
    imageContainer.appendChild(image);
    card.appendChild(imageContainer);
    document.getElementById("products").appendChild(card);

    let name = document.createElement("div");
    name.classList.add("name");
    name.innerText = products[i].name.toUpperCase();
    card.appendChild(name);

    let priceProducts = document.createElement("div");
    priceProducts.classList.add("price");
    priceProducts.innerText = "Giá : " +products[i].price;
    card.appendChild(priceProducts);
}

function filter (value) {
    let x = document.querySelectorAll(".card");
    for (let i = 0; i<x.length; i++) {
        if (value === "All") {
            x[i].removeAttribute("hidden");
        } else if (x[i].classList.contains(value)) {
            x[i].removeAttribute("hidden");
        // phải set cái else cuối cùng này vì khi mình chọn các điều kiện khác trước thì nó đã remove thuộc tính hidden vĩnh viễn rồi
        } else {
            x[i].setAttribute("hidden", "");
        }
    }
}

function searchProduct () {
    let a = document.getElementById("input").value;
    let b = a.toLowerCase();
    let c = a.toUpperCase();
    let x = document.querySelectorAll(".card");
    let k = document.querySelectorAll(".name");

    if (b === "bò" || b === "bo") {
        b = "Bò";
        filter(b);
    } else if (b === "gà" || b === "ga") {
        b = "Gà";
        filter(b);
    } else if (b === "heo") {
        b = "Heo";
        filter(b);
    } else if (b === "vịt" || b === "vit") {
        b = "Vịt";
        filter(b);
    } else if (b === "tôm" || b === "tom") {
        b = "Tôm";
        filter(b);
    } else if (b === "cua") {
        b = "Cua";
        filter(b);
    } else if (b === "cá" || b === "ca") {
        b = "Cá";
        filter(b);
    } else {
        for (let i = 0; i < k.length; i++) {
            if (k[i].innerText.includes(c)) {
                x[i].removeAttribute("hidden");
            } else {
                x[i].setAttribute("hidden", "");
            }
        }
    }
}

