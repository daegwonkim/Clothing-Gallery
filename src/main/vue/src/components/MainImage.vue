<template>
  <div class="div-img_text_wrapper">
    <v-parallax
      :src="require(`@/assets/mainImage/${imgSrc}.jpg`)"
      class="img-page_main_img"
    />
    <div class="div-main_img_text">{{pageTitle}}</div>
  </div>
</template>

<script>
export default {
  props: [
    "pageTitle",
    "imgSrc"
  ]
}
</script>

<style>
.div-img_text_wrapper {
  position: relative;
  overflow: hidden;
}

.img-page_main_img {
  filter: brightness(70%);
}

.div-main_img_text {
  position: absolute;
  text-align: center;
  top: 230px;
  width: 100%;
  color: #fff;
  font-size: 18px;
  font-weight: 300;
  letter-spacing: 2px;
}
</style>