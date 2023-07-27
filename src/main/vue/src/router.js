import Vue from "vue"
import VueRouter from "vue-router"
import Home from "./views/Home"
import Shop from "./views/Shop"
import NewArrivals from "./views/NewArrivals"
import Brands from "./views/Brands"
import Clothing from "./views/Clothing"
import Footwear from "./views/Footwear"
import Accessories from "./views/Accessories"
import Sale from "./views/Sale"
import Contact from "./views/Contact"
import Detail from "./views/Detail"
import Cart from "./views/Cart"
import Wish from "./views/Wish"

Vue.use(VueRouter);

const router = new VueRouter({
    mode: "history",
    routes: [
        {path: "/", component: Home},
        {path: "/shop", component: Shop},
        {path: "/new-arrivals", component: NewArrivals},
        {path: "/brands", component: Brands},
        {path: "/clothing", component: Clothing},
        {path: "/footwear", component: Footwear},
        {path: "/accessories", component: Accessories},
        {path: "/sale", component: Sale},
        {path: "/contact", component: Contact},
        {path: "/detail", component: Detail, name: "detail"},
        {path: "/cart", component: Cart},
        {path: "/wish", component: Wish},
    ],
    scrollBehavior() {
        return { x: 0, y: 0 }
    },
});

export default router;