import React from 'react'
import PureRenderMixin from 'react-addons-pure-render-mixin'
import { hashHistory } from 'react-router'

export default class MessagesForm extends React.Component {
    constructor(props) {
        super(props)
        this.shouldComponentUpdate = PureRenderMixin.shouldComponentUpdate.bind(this)
    }

    onSubmit(e){
        this.props.sendMessage({conversationId: this.props.conversationId, message: this.refs.msg.value})
            /*.then(() => {
            hashHistory.push("/conversations/" + this.props.conversationId)
        })*/
    }


    render() {

        return (
            <div>
                <form onSubmit={this.onSubmit.bind(this)}>
                    <div className="form-group">
                        <label>Message</label>
                        <input className="form-control" ref="msg" />
                    </div>
                    <button type="submit" className="btn btn-fill btn-primary">Envoyer</button>
                </form>
            </div>
        );
    }
}
