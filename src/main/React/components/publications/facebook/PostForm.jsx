/**
 * Created by adib on 15/04/17.
 */
import React from 'react'
import PureRenderMixin from 'react-addons-pure-render-mixin'

export default class PostForm extends React.Component {
    constructor(props) {
        super(props);
        this.shouldComponentUpdate = PureRenderMixin.shouldComponentUpdate.bind(this);
    }

    onSubmit(e){
        e.preventDefault()
        this.props.createPost({link: this.refs.link.value, picture: this.refs.picture.value,
        name: this.refs.name.value, caption: this.refs.caption.value, description: this.refs.description.value})
    }

    render() {
        return <div className="container-fluid">
                           <div className="row">
                            <h5> Nouveau Post: </h5>
                               <div className="col-md-12">
                                   <div className="panel panel-default">
                                       <div className="panel-body">
                                            <form onSubmit={this.onSubmit.bind(this)}>
                                                    <div className="form-group">
                                                        <label>Lien</label>
                                                        <div>
                                                            <input className="form-control" type="text" ref="link" placeholder="Lien" />
                                                        </div>
                                                    </div>
                                                    <div className="form-group">
                                                        <label>Image</label>
                                                        <div>
                                                            <input className="form-control" type="text" ref="picture" placeholder="Image" />
                                                        </div>
                                                    </div>
                                                    <div className="form-group">
                                                        <label>Titre</label>
                                                        <div>
                                                            <input className="form-control" type="text" ref="name" placeholder="Titre" />
                                                        </div>
                                                    </div>
                                                    <div className="form-group">
                                                        <label>Caption</label>
                                                        <div>
                                                            <input className="form-control" type="text" ref="caption" placeholder="Caption" />
                                                        </div>
                                                    </div>
                                                    <div className="form-group">
                                                        <label>Description</label>
                                                        <div>
                                                            <input className="form-control" type="text" ref="description" placeholder="Description" />
                                                        </div>
                                                    </div>
                                                    <div className="form-group">
                                                        <div>
                                                            <button type="submit" className="btn btn-fill btn-primary" >Poster</button>
                                                        </div>
                                                    </div>
                                                </form>
                                            </div>
                                       </div>
                                   </div>
                               </div>
                           </div>
    }
}