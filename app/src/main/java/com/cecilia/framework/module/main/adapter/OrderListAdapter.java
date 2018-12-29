package com.cecilia.framework.module.main.adapter;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.cecilia.framework.R;
import com.cecilia.framework.base.BaseLmrvAdapter;
import com.cecilia.framework.base.BaseViewHolder;
import com.cecilia.framework.common.NetworkConstant;
import com.cecilia.framework.general.BaseGoodBean;
import com.cecilia.framework.listener.OnItemClickListener;
import com.cecilia.framework.module.main.activity.SubmitCommentActivity;
import com.cecilia.framework.module.main.bean.OrderBean;
import com.cecilia.framework.module.order.activity.OrderDetailActivity;
import com.cecilia.framework.utils.ArithmeticalUtil;
import com.cecilia.framework.utils.DialogUtil;
import com.cecilia.framework.utils.LoadImageWithGlide.ImageUtil;
import com.cecilia.framework.utils.LogUtil;
import com.cecilia.framework.utils.ToastUtil;
import com.cecilia.framework.utils.ViewUtil;

public class OrderListAdapter extends BaseLmrvAdapter {

    private OnOrderItemClickListener mOnItemClickListener;
    private int type;
    private int mId;

    public OrderListAdapter(Context context, int type) {
        super(context);
        this.type = type;
    }


    private void initView(BaseViewHolder holder, Object baseGoodBean) {
        TextView delete = holder.getView(R.id.tv_delete);
        TextView comment = holder.getView(R.id.tv_comment);
        TextView buy = holder.getView(R.id.tv_buy);
        TextView name = holder.getView(R.id.tv_name);
        ImageView header = holder.getView(R.id.iv_photo);
        TextView sales = holder.getView(R.id.tv_sales);
        TextView price = holder.getView(R.id.tv_price);
        buy.setVisibility(View.VISIBLE);
        delete.setVisibility(View.VISIBLE);
        comment.setVisibility(View.VISIBLE);
        switch (type) {
            case 0:
                final OrderBean orderBean1 = (OrderBean) baseGoodBean;
                name.setText(orderBean1.getMerchant().getTName());
                sales.setText("商品数量" + orderBean1.getGoodsNum() + "件");
                price.setText(ArithmeticalUtil.getMoneyString(orderBean1.getTTotalMoney()));
                ImageUtil.loadNetworkImage(mContext, NetworkConstant.IMAGE_URL + orderBean1.getFirstGoodsImg(), header, null);
                if (orderBean1.getTStatus() == 0) {
                    buy.setText("订单详情");
                    delete.setText("取消订单");
                    comment.setText("立即购买");
                    delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mOnItemClickListener != null) {
                                mOnItemClickListener.onItemClick("取消订单", orderBean1.getTId());
                            }
                        }
                    });
                    comment.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mOnItemClickListener != null) {
                                mOnItemClickListener.onItemClick("立即购买", orderBean1.getTId());
                            }
                        }
                    });
                    buy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mOnItemClickListener != null) {
                                mOnItemClickListener.onItemClick("",orderBean1.getTId());
                            }
                        }
                    });
                } else if (orderBean1.getTStatus() == 1) {
                    comment.setVisibility(View.GONE);
                    buy.setText("订单详情");
                    delete.setVisibility(View.GONE);
                    buy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mOnItemClickListener != null) {
                                mOnItemClickListener.onItemClick("",orderBean1.getTId());
                            }
                        }
                    });
                } else if (orderBean1.getTStatus() == 2) {
                    comment.setVisibility(View.GONE);
                    buy.setText("订单详情");
                    delete.setText("确认收货");
                    delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mOnItemClickListener != null) {
                                mOnItemClickListener.onItemClick("确认收货", orderBean1.getTId());
                            }

                        }
                    });
                    buy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mOnItemClickListener != null) {
                                mOnItemClickListener.onItemClick("",orderBean1.getTId());
                            }
                        }
                    });
                } else if (orderBean1.getTStatus() == 3) {
                    comment.setText("再次购买");
                    buy.setText("订单详情");
                    delete.setText("立即评价");
                    comment.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mOnItemClickListener != null) {
                                mOnItemClickListener.onItemClick("再次购买", orderBean1.getTId());
                            }
                        }
                    });
                    delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mOnItemClickListener != null) {
                                mOnItemClickListener.onItemClick("立即评价", orderBean1.getTId());
                            }
                        }
                    });
                    buy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mOnItemClickListener != null) {
                                mOnItemClickListener.onItemClick("投诉店家",orderBean1.getTId());
                            }
                        }
                    });
                } else if (orderBean1.getTStatus() == 4) {
                    buy.setText("订单详情");
                    delete.setText("删除订单");
                    comment.setText("已完成");
                    delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mOnItemClickListener != null) {
                                mOnItemClickListener.onItemClick("删除订单", orderBean1.getTId());
                            }
                        }
                    });
                    buy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mOnItemClickListener != null) {
                                mOnItemClickListener.onItemClick("投诉店家",orderBean1.getTId());
                            }
                        }
                    });
                } else if (orderBean1.getTStatus() == 5) {
                    buy.setText("订单详情");
                    delete.setText("删除订单");
                    comment.setText("已关闭");
                    delete.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mOnItemClickListener != null) {
                                mOnItemClickListener.onItemClick("删除订单", orderBean1.getTId());
                            }
                        }
                    });
                    buy.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (mOnItemClickListener != null) {
                                mOnItemClickListener.onItemClick("投诉店家",orderBean1.getTId());
                            }
                        }
                    });
                }
                break;
            case 1:
                final OrderBean orderBean2 = (OrderBean) baseGoodBean;
                name.setText(orderBean2.getMerchant().getTName());
                sales.setText("商品数量" + orderBean2.getGoodsNum() + "件");
                price.setText(ArithmeticalUtil.getMoneyString(orderBean2.getTTotalMoney()));
                ImageUtil.loadNetworkImage(mContext, NetworkConstant.IMAGE_URL + orderBean2.getFirstGoodsImg(), header, null);
                buy.setText("订单详情");
                delete.setText("取消订单");
                comment.setText("立即购买");
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onItemClick("取消订单", orderBean2.getTId());
                        }
                    }
                });
                comment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onItemClick("立即购买", orderBean2.getTId());
                        }
                    }
                });
                buy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onItemClick("",orderBean2.getTId());
                        }
                    }
                });
                break;
            case 2:
                final OrderBean orderBean3 = (OrderBean) baseGoodBean;
                name.setText(orderBean3.getMerchant().getTName());
                sales.setText("商品数量" + orderBean3.getGoodsNum() + "件");
                price.setText(ArithmeticalUtil.getMoneyString(orderBean3.getTTotalMoney()));
                ImageUtil.loadNetworkImage(mContext, NetworkConstant.IMAGE_URL + orderBean3.getFirstGoodsImg(), header, null);
                comment.setVisibility(View.GONE);
                buy.setText("订单详情");
                delete.setVisibility(View.GONE);
                buy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onItemClick("",orderBean3.getTId());
                        }
                    }
                });;
                break;
            case 3:
                final OrderBean orderBean4 = (OrderBean) baseGoodBean;
                name.setText(orderBean4.getMerchant().getTName());
                sales.setText("商品数量" + orderBean4.getGoodsNum() + "件");
                price.setText(ArithmeticalUtil.getMoneyString(orderBean4.getTTotalMoney()));
                ImageUtil.loadNetworkImage(mContext, NetworkConstant.IMAGE_URL + orderBean4.getFirstGoodsImg(), header, null);
                comment.setVisibility(View.GONE);
                buy.setText("订单详情");
                delete.setText("确认收货");
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onItemClick("确认收货", orderBean4.getTId());
                        }

                    }
                });
                buy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onItemClick("",orderBean4.getTId());
                        }
                    }
                });
                break;
            case 4:
                final OrderBean orderBean5 = (OrderBean) baseGoodBean;
                name.setText(orderBean5.getMerchant().getTName());
                sales.setText("商品数量" + orderBean5.getGoodsNum() + "件");
                price.setText(ArithmeticalUtil.getMoneyString(orderBean5.getTTotalMoney()));
                ImageUtil.loadNetworkImage(mContext, NetworkConstant.IMAGE_URL + orderBean5.getFirstGoodsImg(), header, null);
                comment.setText("再次购买");
                buy.setText("订单详情");
                delete.setText("立即评价");
                comment.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onItemClick("再次购买", orderBean5.getTId());
                        }
                    }
                });
                delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onItemClick("立即评价", orderBean5.getTId());
                        }
                    }
                });
                buy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onItemClick("投诉店家",orderBean5.getTId());
                        }
                    }
                });
                break;
            case 5:
                final BaseGoodBean goodBean = (BaseGoodBean) baseGoodBean;
                delete.setVisibility(View.GONE);
                comment.setVisibility(View.GONE);
                buy.setText("取消收藏");
                name.setText(goodBean.getTGoodsTitle());
                price.setText(ArithmeticalUtil.getMoneyString(goodBean.getTPrice()));
                ImageUtil.loadNetworkImage(mContext, NetworkConstant.IMAGE_URL + goodBean.getTPic(), header, null);
                buy.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (mOnItemClickListener != null) {
                            mOnItemClickListener.onItemClick("取消收藏", goodBean.getTId());
                        }
                    }
                });
                break;
            case 6:
                break;
            case 7:
                break;
            case 8:
                break;
            case 9:
                break;
        }
    }

    public void setOnItemBuyClickListener(OnOrderItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    @Override
    public BaseViewHolder onCreateRecyclerViewHolder(LayoutInflater layoutInflater, ViewGroup parent, int viewType) {
        return new BaseViewHolder(layoutInflater.inflate(R.layout.item_rv_order, parent, false));
    }

    @Override
    public void onBindRecyclerViewHolder(BaseViewHolder holder, Object data) {
        initView(holder, data);
    }

    public interface OnOrderItemClickListener {
        void onItemClick(String info, int id);
    }

}
