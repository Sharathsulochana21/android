/**
 * Created by Sharath Kumar T N on 04-12-2018.
 */
public class AcceptRejectAdapter extends
    RecyclerView.Adapter<AcceptRejectAdapter.SingleItemRowHolder> {

  private Context context;
  private ArrayList<AcceptRejectModel> acceptRejectModels;
  private AcceptRejectModel acceptRejectModel;
  private ItemClickListener itemClickListener;

  public AcceptRejectAdapter(ArrayList<AcceptRejectModel> itemModels, Context mContext,
      ItemClickListener itemClickListener) {
    this.acceptRejectModels = itemModels;
    this.context = mContext;
    this.itemClickListener = itemClickListener;
  }

  @NonNull
  @Override
  public SingleItemRowHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View rootView = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false);
    RecyclerView.LayoutParams lp = new RecyclerView.LayoutParams(
        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
    rootView.setLayoutParams(lp);
    return new SingleItemRowHolder(rootView);
  }

  @Override
  public void onBindViewHolder(@NonNull final SingleItemRowHolder singleItemRowHolder,
      final int position) {
    acceptRejectModel = acceptRejectModels.get(position);
    singleItemRowHolder.tvName.setText(acceptRejectModel.getName());
    singleItemRowHolder.tvPhoneNumber.setText(acceptRejectModel.getPhoneNumber());
    singleItemRowHolder.btnAccept.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        itemClickListener
            .onAcceptClickListener(singleItemRowHolder.getAdapterPosition(), acceptRejectModel, 1);
      }
    });

    singleItemRowHolder.btnReject.setOnClickListener(new OnClickListener() {
      @Override
      public void onClick(View view) {
        itemClickListener
            .onAcceptClickListener(singleItemRowHolder.getAdapterPosition(), acceptRejectModel, 0);
      }
    });
  }

  @Override
  public int getItemCount() {
    return (null != acceptRejectModels ? acceptRejectModels.size() : 0);
  }

  public class SingleItemRowHolder extends RecyclerView.ViewHolder {

    TextView tvName;
    TextView tvPhoneNumber;
    Button btnAccept;
    Button btnReject;

    private SingleItemRowHolder(View itemView) {
      super(itemView);
      this.tvName = itemView.findViewById(R.id.tvName);
      this.tvPhoneNumber = itemView.findViewById(R.id.tvPhoneNumber);
      this.btnAccept = itemView.findViewById(R.id.btnAccept);
      this.btnReject = itemView.findViewById(R.id.btnReject);
    }
  }
}