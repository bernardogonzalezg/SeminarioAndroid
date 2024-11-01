import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ar.edu.unicen.seminario.databinding.ListItemMovieBinding
import ar.edu.unicen.seminario.ddl.models.Movie
import ar.edu.unicen.seminario.ui.MovieDetailActivity
import com.bumptech.glide.Glide

class MovieAdapter(
    private val movies: List<Movie>,
    private val onClick: (Movie) -> Unit
) : RecyclerView.Adapter<MovieAdapter.MovieViewHolder>() {

    inner class MovieViewHolder(private val binding: ListItemMovieBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: Movie) {
            binding.movieTitle.text = movie.title
            //binding.movieOverview.text = movie.overview

            // Cargar la imagen con Glide
            Glide.with(binding.moviePoster.context)
                .load("https://image.tmdb.org/t/p/w500${movie.poster_path}") // Asegúrate de que la URL esté bien formada
                .into(binding.moviePoster)

            binding.root.setOnClickListener {
                val context = binding.root.context
                val intent = Intent(context, MovieDetailActivity::class.java)
                intent.putExtra("movie_title", movie.title)
                intent.putExtra("movie_overview", movie.overview)
                intent.putExtra("movie_poster_path", movie.poster_path)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val binding = ListItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }

    override fun getItemCount() = movies.size
}