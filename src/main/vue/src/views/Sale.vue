<template>
  <div>
    <MainImage :pageTitle="pageTitle" :imgSrc="imgSrc" />

    <div class="div-product_list">
      <!-- Product List -->
      <List :category="category" />
      <!-- Product List -->
    </div>
  </div>
</template>

<script>
import List from "../components/List.vue";
import MainImage from "../components/MainImage.vue";

export default {
  components: {
    List,
    MainImage
  },

  data() {
    return {
      category: "sale",
      pageTitle: "SALE",
      imgSrc: "sale_main"
    };
  },
};
</script>

<style>
.div-product_list {
  margin: 40px 0 0 15px;
}
</style>