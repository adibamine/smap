import React from 'react'
import PureRenderMixin from 'react-addons-pure-render-mixin'
import { hashHistory } from 'react-router'

export default class TermForm extends React.Component {
    constructor(props) {
        super(props)
        this.shouldComponentUpdate = PureRenderMixin.shouldComponentUpdate.bind(this)
    }

    onSubmitFr(e){
        e.preventDefault()
        this.props.saveTerm({term: this.refs.termFr.value, lang: "fr"}).then(() => {
            hashHistory.push('/terms')
        })
    }

    onSubmitAr(e){
        e.preventDefault()
        this.props.saveTerm({term: this.refs.termAr.value, lang: "ar"}).then(() => {
            hashHistory.push('/terms')
        })
    }

    render() {

        return (
            <div>
                <form onSubmit={this.onSubmitFr.bind(this)}>
                    <div className="form-group">
                        <label>Mot clé Français</label>
                        <input className="form-control" ref="termFr" />
                    </div>
                    <button type="submit" className="btn btn-fill btn-primary">Save</button>
                </form>
                <hr />
                <form onSubmit={this.onSubmitAr.bind(this)}>
                    <div className="form-group">
                        <label>Mot clé Arabe</label>
                        <input className="form-control" ref="termAr" />
                    </div>
                    <button type="submit" className="btn btn-fill btn-primary">Save</button>
                </form>
            </div>
        );
    }
}
