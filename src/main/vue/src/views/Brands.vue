<template>
  <div class="d-flex">

    <!-- Designer -->
    <Designer :brands="brands" />
    <!-- Designer End -->

    <!-- Product List -->
    <div class="div-product_list">
      <List :category="category" :itemWidth="itemWidth" :itemHeight="itemHeight" />
    </div>
    <!-- Product List End -->

  </div>
</template>

<script>
import List from "../components/List.vue";
import Designer from "../components/Designer.vue"

export default {
  components: {
    List,
    Designer
  },

  data() {
    return {
      category: "all",
      itemWidth: 291,
      itemHeight: 291,
      brands: [
        {name: "All", isActive: true},
        {name: "A.P.C.", isActive: false},
        {name: "Advisory Board Crystals", isActive: false},
        {name: "Apostle Club", isActive: false},
        {name: "Billionaire boys club", isActive: false},
        {name: "Birkenstock", isActive: false},
        {name: "BTFL", isActive: false},
        {name: "Cold World Frozen Goods", isActive: false},
        {name: "Clae", isActive: false},
        {name: "Clarks", isActive: false},
        {name: "Craft + Foster", isActive: false},
        {name: "Druthers NYC", isActive: false},
        {name: "Evolve", isActive: false},
        {name: "Everyday Labour", isActive: false},
        {name: "G-Star", isActive: false},
        {name: "G-Shock", isActive: false},
        {name: "Herschel", isActive: false},
        {name: "Honor the Gift", isActive: false},
        {name: "Jason Markk", isActive: false},
        {name: "John Elliott", isActive: false},
        {name: "Karhu", isActive: false},
        {name: "Ksubi", isActive: false},
        {name: "LiteYear", isActive: false},
        {name: "Chinatown market", isActive: false},
        {name: "Marni", isActive: false},
        {name: "New Balance", isActive: false},
        {name: "New Era", isActive: false},
        {name: "Nudie Jeans", isActive: false},
        {name: "On Running", isActive: false},
        {name: "Planes", isActive: false},
        {name: "Penfield", isActive: false},
        {name: "Pleasures", isActive: false},
        {name: "Qilo NYC", isActive: false},
        {name: "Rains", isActive: false},
        {name: "Reigning Champ", isActive: false},
        {name: "RetroSuperFuture", isActive: false},
        {name: "Salt & Stone", isActive: false},
        {name: "Saturdays NYC", isActive: false},
        {name: "Saucony", isActive: false},
        {name: "Stampd", isActive: false},
        {name: "Stussy", isActive: false},
        {name: "Taschen", isActive: false},
        {name: "the Unbranded Brand", isActive: false},
        {name: "Wax London", isActive: false},
        {name: "Wings + Horn", isActive: false},
      ],
    };
  },
};
</script>

<style>
.div-product_list {
  margin: 40px 0 0 0px;
  width:100%;
}
</style>