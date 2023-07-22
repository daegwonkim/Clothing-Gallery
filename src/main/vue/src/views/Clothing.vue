<template>
  <div>
    <MainImage :pageTitle="pageTitle" :imgSrc="imgSrc" />

    <div class="d-flex">
      <!-- Designer -->
      <Designer />
      <!-- Designer End -->

      <!-- Product List -->
      <div class="div-product_list">
        <List :category="category" :itemWidth="itemWidth" :itemHeight="itemHeight" />
      </div>
      <!-- Product List End -->
    </div>
  </div>
</template>

<script>
import MainImage from "../components/MainImage.vue"
import Designer from "../components/Designer.vue"
import List from "../components/List.vue";

export default {
  components: {
    MainImage,
    Designer,
    List,
  },

  data() {
    return {
      category: "clothing",
      pageTitle: "CLOTHING",
      imgSrc: "clothing_main",
      itemWidth: 291,
      itemHeight: 291,
    }
  },
}
</script>

<style>

</style>