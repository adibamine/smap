/**
 * Created by adib on 15/04/17.
 */
import React from 'react'
import PureRenderMixin from 'react-addons-pure-render-mixin'

export default class PostLinkForm extends React.Component {
    constructor(props) {
        super(props);
        this.shouldComponentUpdate = PureRenderMixin.shouldComponentUpdate.bind(this);
    }

    onSubmit(e){
        e.preventDefault()
        console.log("here")
        this.props.createPostLink({link: this.refs.link.value, title: this.refs.title.value})
    }

    render() {
        return <div className="container-fluid">
                           <div className="row">
                            <h5> Nouveau Lien: </h5>
                               <div className="col-md-12">
                                   <div className="panel panel-default">
                                       <div className="panel-body">
                                            <form onSubmit={this.onSubmit.bind(this)}>
                                                    <div className="form-group">
                                                        <label>Lien</label>
                                                        <div className="form-group">
                                                            <input className="form-control" type="text" ref="link" placeholder="Lien" />
                                                        </div>
                                                    </div>
                                                    <div className="form-group">
                                                        <label>Titre</label>
                                                        <div className="form-group">
                                                            <input className="form-control" type="text" ref="title" placeholder="Titre" />
                                                        </div>
                                                    </div>
                                                    <div className="form-group">
                                                        <div>
                                                            <button type="submit" className="btn btn-fill btn-primary">Poster</button>
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