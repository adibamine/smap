import React from 'react'
import PureRenderMixin from 'react-addons-pure-render-mixin'
import { hashHistory } from 'react-router'

export default class MonitoringForm extends React.Component {
    constructor(props) {
        super(props)
        this.shouldComponentUpdate = PureRenderMixin.shouldComponentUpdate.bind(this)
    }

    onSubmit(e){
        e.preventDefault()
        hashHistory.push(`/monitoring/${this.refs.term_ar.value}/${this.refs.term_fr.value}`)
    }

    render() {

        return (
            <div>
            <form onSubmit={this.onSubmit.bind(this)}>
            <div className="form-group">
                <label>Terme FR</label>
                <input className="form-control" ref="term_fr" />
            </div>
            <div className="form-group">
                <label>Terme AR</label>
                <input className="form-control" ref="term_ar" />
            </div>
            <button type="submit" className="btn btn-fill btn-primary">Send</button>
        </form>
            </div>
    );
    }
}
