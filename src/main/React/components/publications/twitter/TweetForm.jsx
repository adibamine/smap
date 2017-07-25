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
        this.props.createTweet({text: this.refs.text.value})
    }

    render() {
        return <div className="container-fluid">
                    <div className="row">
                        <div className="col-md-12">
                            <h5> Nouveau tweet: </h5>
                            <div className="panel panel-default">
                                <div className="panel-body">
                                    <form onSubmit={this.onSubmit.bind(this)}>
                                        <div className="form-group">
                                            <label>Tweet</label>
                                            <div>
                                                <input className="form-control" type="text" ref="text" placeholder="Tweet" />
                                            </div>
                                        </div>
                                        <div>
                                            <div>
                                                <button type="submit" className="btn btn-fill btn-primary">Envoyer</button>
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