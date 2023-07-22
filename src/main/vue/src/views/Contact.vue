<template>
  <div>
    <MainImage :pageTitle="pageTitle" :imgSrc="imgSrc" />

    <div class="d-flex div-us_wrapper">
      <div class="div-us">
        <div class="div-us_title">CONTACT US</div><br>
        <div class="div-us_content">
          <div>
            Need help with your order?<br>
            Have a question about sizing?<br>
            Feel free to get in touch with us with any questions you may have!
          </div>
          <div>Email <a href="mailto:evolveclothinggallery@gmail.com">evolveclothinggallery@gmail.com</a></div>
          <div>Phone 908.231.8933</div>
        </div>
      </div>
      <div class="div-us">
        <div class="div-us_title">VISIT US</div><br>
        <div class="div-us_content">
          <div class="div-bold">
            Monday-Friday 10:00am – 6:00pm<br>
            Saturday 10:00am – 7:00pm<br>
            Sunday 12:00pm – 5:00pm
          </div>
          <div>
            Due to Covid-19, hours may be subject to change.<br>
            Call ahead to inquire about closing time.
          </div>
          <div class="div-bold">
            80 W Main Street Somerville, NJ 08876
          </div>
        </div>
      </div>
    </div>

    <iframe
      width="100%"
      height="300"
      style="border: 0"
      loading="lazy"
      allowfullscreen
      src="https://www.google.com/maps/embed/v1/place?key=AIzaSyCRVEtb5bJW6zx45iFF7fB8TTRRdYXMUNI&q=Evolve Clothing Gallery&region=KR&language=en"
    ></iframe>
  </div>
</template>

<script>
import Designer from '../components/Designer.vue'
import MainImage from "../components/MainImage.vue"

export default {
  components: {
    MainImage,
    Designer,
  },

  data() {
    return {
      pageTitle: "CONTACT",
      imgSrc: "contact_main"
    }
  },
}
</script>

<style>
.div-us_wrapper {
  justify-content: space-around;
  margin: 60px;
}

.div-us {
  width: 360px;
  line-height: 25.5px;
}

.div-us_title {
  font-size: 13px;
  letter-spacing: 5px;
}

.div-us_content > div {
  padding-bottom: 10px;
}

.div-bold {
  font-weight: bold;
}
</style>