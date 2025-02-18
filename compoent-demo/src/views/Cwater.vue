<template>
    <div>
    <h1>电影</h1>
    
    <Movie v-for="movie in movies" :key="movie.id" :title="movie.title" :rating="movie.rating"></Movie>

</div>
</template>
<script>
import Movie from '@/components/Movie.vue';
export default{
    components:{
        Movie
    },
    data:function(){
            return{
                movies:[
                  {id:1,title:"金刚狼1",rating:8.7},
                  {id:2,title:"金刚狼2",rating:8.7},
                  {id:3,title:"金刚狼3",rating:8.7},
                ]
            }
          },
}

</script>