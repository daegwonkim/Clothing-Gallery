const { defineConfig } = require('@vue/cli-service')

module.exports = defineConfig({
  transpileDependencies: [
    'vuetify'
  ],
  outputDir: '../resources/static', // Build Directory
  devServer: {
      proxy: 'http://localhost:8080', // Spring Boot Server
  },
  lintOnSave : false,
  configureWebpack:{
    performance: {
      hints: false
    },
    optimization: {
      splitChunks: {
        minSize: 10000,
        maxSize: 250000,
      }
    }
  },
})
