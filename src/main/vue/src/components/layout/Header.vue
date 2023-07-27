<template>
  <div>
    <v-system-bar app color="black">
      <div class="div-system_bar_text">Free domestic ground shipping on orders $200. + over.  Order online - Free Pick up in store</div>
    </v-system-bar>

    <v-app-bar app flat color="white" height="70">
      <!-- Home Button -->
      <router-link :to="'/'" class="a-to_home">
        <div>
          <v-img src="../../../public/logo.webp" />
        </div>
      </router-link>
      <!-- Home Button End -->

      <!-- Toolbar Menu -->
      <v-row class="ml-16 pl-16" justify="center">
        <v-col
          class="toolbar-btn"
          cols="auto"
          v-for="(item, index) in toolbar_btn_list"
          :key="index"
        >
          <router-link :to="item.url">{{ item.name }}</router-link>
        </v-col>
      </v-row>
      <!-- Toolbar Menu End -->

      <!-- Quick Menu -->
      <!-- Search -->
      <!-- <v-btn icon>
        <v-icon>mdi-magnify</v-icon>
      </v-btn> -->

      <!-- Wish -->
      <router-link class="a-quick_menu" to="/wish">
        <font-awesome-icon icon="fa-solid fa-heart" size="xl" />
      </router-link>

      <!-- Cart -->
      <router-link class="a-quick_menu" to="/cart">
        <font-awesome-icon icon="cart-shopping" size="lg" />
      </router-link>

      <!-- Profile -->
      <router-link class="a-quick_menu" to="/">
        <font-awesome-icon icon="user" size="lg" />
      </router-link>
      <!-- Quick Menu End -->
    </v-app-bar>
  </div>
</template>

<script>
export default {
  name: "header",

  data() {
    return {
      toolbar_btn_list: [
        { name: "SHOP", url: "/shop" },
        { name: "NEW ARRIVALS", url: "/new-arrivals" },
        { name: "BRANDS", url: "/brands" },
        { name: "CLOTHING", url: "/clothing" },
        { name: "FOOTWEAR", url: "/footwear" },
        { name: "ACCESSORIES", url: "/accessories" },
        { name: "SALE", url: "/sale" },
        { name: "CONTACT", url: "/contact" },
      ],
    };
  },
};
</script>

<style>
.div-system_bar_text {
  width: 100%; 
  text-align: center; 
  color: #ddd;
  font-size: 13px;
  font-weight: 100;
}

a {
  text-decoration: none;
  color: #666 !important;
}

a:hover {
  color: black !important;
}

.a-to_home {
  display:block; 
  width:40px; 
  height:30px;
}

.a-to_home > div {
  width: 130px;
  margin: -8px 0 0 0;
}

.a-quick_menu {
  width: 45px;
  text-align: center;
}

</style>