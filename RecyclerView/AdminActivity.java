public class AdminActivity extends AppCompatActivity implements ItemClickListener,
    SwipeRefreshLayout.OnRefreshListener {

  SwipeRefreshLayout mSwipeRefreshLayout;
  AcceptRejectAdapter adapter;
  RecyclerView recyclerView;
  ItemOffsetDecoration itemDecoration;
  ArrayList<AcceptRejectModel> acceptRejectData = new ArrayList<>();

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_admin);

    mSwipeRefreshLayout = findViewById(R.id.swipe_container);
    itemDecoration = new ItemOffsetDecoration(this, R.dimen.pv_pin_view_item_line_width);
    recyclerView = findViewById(R.id.recycler_view);
    recyclerView.addItemDecoration(itemDecoration);

    fetchData();

    mSwipeRefreshLayout.setOnRefreshListener(this);
    mSwipeRefreshLayout.setColorSchemeResources(R.color.colorPrimary,
        android.R.color.holo_green_dark,
        android.R.color.holo_orange_dark,
        android.R.color.holo_blue_dark);
    mSwipeRefreshLayout.setRefreshing(false);
    mSwipeRefreshLayout.setOnRefreshListener(
        new SwipeRefreshLayout.OnRefreshListener() {
          @Override
          public void onRefresh() {
            fetchData();
            mSwipeRefreshLayout.setRefreshing(false);
          }
        }
    );
  }

  @Override
  public void onRefresh() {

  }

  public class ItemOffsetDecoration extends RecyclerView.ItemDecoration {

    private int mItemOffset;

    private ItemOffsetDecoration(int itemOffset) {
      mItemOffset = itemOffset;
    }

    private ItemOffsetDecoration(@NonNull Context context, @DimenRes int itemOffsetId) {
      this(context.getResources().getDimensionPixelSize(itemOffsetId));
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view,
        @NonNull RecyclerView parent,
        @NonNull RecyclerView.State state) {
      super.getItemOffsets(outRect, view, parent, state);
      outRect.set(mItemOffset, mItemOffset, mItemOffset, mItemOffset);
    }
  }

  @Override
  public void onAcceptClickListener(final int pos, AcceptRejectModel item, int acceptOrReject) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    if (acceptOrReject == 1) {
      builder.setMessage(R.string.confirm_sms)
          .setPositiveButton("Ok", new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
              acceptRejectData.remove(pos);
              adapter.notifyDataSetChanged();
            }
          })
          .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
          });
    } else {
      builder.setMessage(R.string.delete_request)
          .setPositiveButton("Ok", new OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
              acceptRejectData.remove(pos);
              adapter.notifyDataSetChanged();
            }
          })
          .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {

            }
          });
    }
    AlertDialog dialog = builder.create();
    dialog.show();
  }

  public void fetchData() {
    acceptRejectData.add(new AcceptRejectModel("Captain America", "12486543"));
    acceptRejectData.add(new AcceptRejectModel("Steve Roger", "2346546"));
    acceptRejectData.add(new AcceptRejectModel("Thor", "43687366736"));
    acceptRejectData.add(new AcceptRejectModel("Loki", "44242443"));
    acceptRejectData.add(new AcceptRejectModel("Black Widow", "9643643644"));
    acceptRejectData.add(new AcceptRejectModel("Thanos", "3545456436"));
    acceptRejectData.add(new AcceptRejectModel("Gamora", "7767663643"));
    acceptRejectData.add(new AcceptRejectModel("Hulk", "647676769"));
    acceptRejectData.add(new AcceptRejectModel("Dr.Strange", "545547577"));
    acceptRejectData.add(new AcceptRejectModel("Odin", "43434364364"));
    acceptRejectData.add(new AcceptRejectModel("Hela", "3647676447"));
    acceptRejectData.add(new AcceptRejectModel("Ant Man", "6769786969"));
    acceptRejectData.add(new AcceptRejectModel("Heimdal", "6976976976"));

    recyclerView.setHasFixedSize(true);
    adapter = new AcceptRejectAdapter(acceptRejectData, this, this);
    recyclerView
        .setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    recyclerView.setAdapter(adapter);

  }
}